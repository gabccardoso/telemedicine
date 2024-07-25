package com.hackaton.telemedicine.infrastructure.controllers.dto;

import com.hackaton.telemedicine.entities.Prontuario;

public class ProntuarioDTOMapper {

    public Prontuario toDomain (ProntuarioDTO prontuarioDTO) {
        return new Prontuario(prontuarioDTO.paciente(), prontuarioDTO.arquivosProntuarioList());
    }

    public ProntuarioDTO toDto (Prontuario prontuario) {
        return new ProntuarioDTO(prontuario.paciente(), prontuario.arquivosProntuarioList());
    }
}
