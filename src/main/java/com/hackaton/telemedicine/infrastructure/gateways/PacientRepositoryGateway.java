package com.hackaton.telemedicine.infrastructure.gateways;

import com.hackaton.telemedicine.application.gateways.PacienteGateway;
import com.hackaton.telemedicine.entities.Paciente;
import com.hackaton.telemedicine.infrastructure.persistence.PacienteEntity;
import com.hackaton.telemedicine.infrastructure.persistence.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;

public class PacientRepositoryGateway implements PacienteGateway {

    private final PacientEntityMapper pacientEntityMapper;
    private final PacienteRepository pacienteRepository;

    public PacientRepositoryGateway(PacientEntityMapper pacientEntityMapper, PacienteRepository pacienteRepository) {
        this.pacientEntityMapper = pacientEntityMapper;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente createPacient(Paciente paciente) {
        PacienteEntity pacienteEntity = pacienteRepository.findByCpf(paciente.cpf());
        if(pacienteEntity != null) throw new IllegalArgumentException("Já existe um paciente com esse CPF");
        pacienteEntity = pacienteRepository.save(pacientEntityMapper.toEntity(paciente));
        return pacientEntityMapper.toDomain(pacienteEntity);
    }

    @Override
    public Paciente updatePacient(Paciente paciente) {
        PacienteEntity pacienteEntity = pacienteRepository.findByCpf(paciente.cpf());
        if(pacienteEntity == null) throw new EntityNotFoundException("Paciente com cpf " + paciente.cpf() + " não encontrado");
        pacienteEntity.setEmail(paciente.email());
        pacienteEntity.setPhone(paciente.phone());
        pacienteEntity.setEndereco(paciente.endereco());
        pacienteEntity.setNome(paciente.nome());
        pacienteRepository.save(pacienteEntity);
        return pacientEntityMapper.toDomain(pacienteEntity);
    }

    @Override
    public void deletePacient(String cpf) {
        pacienteRepository.deleteByCpf(cpf);
    }

}
