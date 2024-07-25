package com.hackaton.telemedicine.application.useCases;

import com.hackaton.telemedicine.application.gateways.ConsultaGateway;
import com.hackaton.telemedicine.entities.Consulta;
import com.hackaton.telemedicine.infrastructure.controllers.dto.ConsultaPendenteDTO;

import java.util.List;

public class ConsultaInteractor {

    private final ConsultaGateway consultaGateway;

    public ConsultaInteractor(ConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    public void criarConsulta(Consulta consulta){
        consultaGateway.criarConsulta(consulta);
    }

    public void editarConsulta(Consulta consulta, Long idConsulta){
        consultaGateway.editarConsulta(consulta, idConsulta);
    }

    public void excluirConsulta(Long idConsulta){
        consultaGateway.excluirConsulta(idConsulta);
    }

    public List<ConsultaPendenteDTO> buscaPendentes(String crm){
        return consultaGateway.buscaPendentes(crm);
    }
    public void alteraStatusConsulta(Long idConsulta, Boolean consultaAprovada) throws Exception {
        consultaGateway.alteraStatusConsulta(idConsulta, consultaAprovada);
    }
}
