package com.hackaton.telemedicine.infrastructure.gateways;

import com.hackaton.telemedicine.Enums.StatusAgendamento;
import com.hackaton.telemedicine.application.gateways.ConsultaGateway;
import com.hackaton.telemedicine.entities.Consulta;
import com.hackaton.telemedicine.infrastructure.controllers.dto.ConsultaPendenteDTO;
import com.hackaton.telemedicine.infrastructure.gateways.utils.GoogleMeetLinkGenerator;
import com.hackaton.telemedicine.infrastructure.persistence.*;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ConsultaRepositoryGateway implements ConsultaGateway {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final GoogleMeetLinkGenerator googleMeetLinkGenerator;

    public ConsultaRepositoryGateway(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, DisponibilidadeRepository disponibilidadeRepository, GoogleMeetLinkGenerator googleMeetLinkGenerator) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.googleMeetLinkGenerator = googleMeetLinkGenerator;
    }

    @Override
    public void criarConsulta(Consulta consulta) {
        PacienteEntity pacienteEntity = pacienteRepository.findByCpf(consulta.cpf());
        MedicoEntity medicoEntity = medicoRepository.findByCrm(consulta.crm());
        DisponibilidadeEntity disponibilidade = verificaDisponibilidadeDeHorario(consulta, medicoEntity.getId());

        ConsultaEntity consultaEntity = new ConsultaEntity(pacienteEntity, medicoEntity, consulta.data(),
                consulta.inicio(), consulta.fim(), null, StatusAgendamento.PENDENTE.name());
        disponibilidadeRepository.deleteById(disponibilidade.getId());
        consultaRepository.save(consultaEntity);
    }

    @Override
    public void editarConsulta(Consulta consulta, Long idConsulta) {
        ConsultaEntity consultaEntity = consultaRepository.findById(idConsulta).orElseThrow(() -> new
                        EntityNotFoundException("Consulta com id " + idConsulta + " não encontrada"));
        DisponibilidadeEntity disponibilidade = verificaDisponibilidadeDeHorario(consulta, consultaEntity.getMedico().getId());
        consultaEntity.setData(consulta.data());
        consultaEntity.setInicio(consulta.inicio());
        consultaEntity.setFim(consulta.fim());
        consultaEntity.setMedico(medicoRepository.findByCrm(consulta.crm()));
        disponibilidadeRepository.deleteById(disponibilidade.getId());
        consultaRepository.save(consultaEntity);
    }

    @Override
    public void excluirConsulta(Long idConsulta) {
        consultaRepository.deleteById(idConsulta);
    }

    @Override
    public List<ConsultaPendenteDTO> buscaPendentes(String crm) {
        MedicoEntity medicoEntity = medicoRepository.findByCrm(crm);
        List<ConsultaEntity> consultaEntityList = consultaRepository.findByMedicoAndStatusConsulta(medicoEntity,
                StatusAgendamento.PENDENTE.name()).orElseThrow(() -> new EntityNotFoundException("Consultas não econtradas"));
        List<ConsultaPendenteDTO> consultaPendenteDTOList = new ArrayList<>();
        for(ConsultaEntity consulta : consultaEntityList){
            consultaPendenteDTOList.add(new ConsultaPendenteDTO(consulta.getId(), consulta.getPaciente().getNome(),
                    consulta.getData(), consulta.getInicio()));
        }
        return consultaPendenteDTOList;
    }

    @Override
    public void alteraStatusConsulta(Long idConsulta, Boolean consultaAprovada) throws Exception {
        ConsultaEntity consulta = consultaRepository.findById(idConsulta).orElseThrow(() -> new
                EntityNotFoundException("Consulta com id " + idConsulta + " não encontrada"));
        consulta.setStatusConsulta(consultaAprovada.equals(true) ? StatusAgendamento.APROVADO.name()
                : StatusAgendamento.RECUSADO.name());
        List<String> emails = new ArrayList<>();
        emails.add(consulta.getMedico().getEmail());
        emails.add(consulta.getPaciente().getEmail());
        String link = googleMeetLinkGenerator.createTeleconsultaEvent("Consulta",
                "Consulta médica com "+consulta.getMedico().getNome(), consulta.getData(), consulta.getInicio(),
                consulta.getFim(), emails);
        consulta.setLinkTeleconsulta(link);
        consultaRepository.save(consulta);
    }

    private DisponibilidadeEntity verificaDisponibilidadeDeHorario(Consulta consulta, Long idMedico){
        return disponibilidadeRepository.findBymedicoIdAndDataAndHorario(idMedico,
                consulta.data(), consulta.inicio(), consulta.fim()).orElseThrow(
                        () -> new EntityNotFoundException("Horário não disponível"));
    }
}
