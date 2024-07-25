package com.hackaton.telemedicine.infrastructure.controllers.dto;

import com.hackaton.telemedicine.entities.Consulta;

import java.util.ArrayList;
import java.util.List;

public class ConsultaDTOMapper {

    public ConsultaDTO toDto (Consulta consulta){
        return new ConsultaDTO(consulta.crm(), consulta.cpf(), consulta.data(), consulta.inicio(), consulta.fim());
    }

    public Consulta toDomain (ConsultaDTO consultaDTO){
        return new Consulta(consultaDTO.crm(), consultaDTO.cpf(), consultaDTO.data(), consultaDTO.inicio(), consultaDTO.fim());
    }
}
