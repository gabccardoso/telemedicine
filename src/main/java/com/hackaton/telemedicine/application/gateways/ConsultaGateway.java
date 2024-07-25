package com.hackaton.telemedicine.application.gateways;

import com.hackaton.telemedicine.entities.Consulta;
import com.hackaton.telemedicine.infrastructure.controllers.dto.ConsultaDTOMapper;
import com.hackaton.telemedicine.infrastructure.controllers.dto.ConsultaPendenteDTO;

import java.util.List;

public interface ConsultaGateway {

    void criarConsulta(Consulta consulta);

    void editarConsulta(Consulta consulta, Long idConsulta);

    void excluirConsulta(Long idConsulta);

    List<ConsultaPendenteDTO> buscaPendentes(String crm);
    void alteraStatusConsulta(Long idConsulta, Boolean consultaAprovada) throws Exception;
}
