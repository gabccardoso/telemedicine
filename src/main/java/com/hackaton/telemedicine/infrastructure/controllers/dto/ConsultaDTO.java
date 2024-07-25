package com.hackaton.telemedicine.infrastructure.controllers.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record ConsultaDTO(String crm, String cpf, LocalDate data, LocalTime inicio, LocalTime fim) {
}
