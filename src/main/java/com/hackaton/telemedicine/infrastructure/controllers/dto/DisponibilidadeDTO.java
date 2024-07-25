package com.hackaton.telemedicine.infrastructure.controllers.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record DisponibilidadeDTO(Long id, Long medicoId, LocalDate data, LocalTime inicio, LocalTime fim) {
}
