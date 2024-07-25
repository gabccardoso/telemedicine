package com.hackaton.telemedicine.infrastructure.controllers.dto;

import com.hackaton.telemedicine.entities.ArquivosProntuario;
import com.hackaton.telemedicine.entities.Paciente;

import java.util.List;

public record ProntuarioDTO (Paciente paciente, List<ArquivosProntuario> arquivosProntuarioList) {
}
