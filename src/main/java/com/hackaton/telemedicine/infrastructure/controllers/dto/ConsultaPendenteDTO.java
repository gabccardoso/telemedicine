package com.hackaton.telemedicine.infrastructure.controllers.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public record ConsultaPendenteDTO(Long idConsulta, String nomePaciente, LocalDate data, LocalTime inicio) {
}
