package com.hackaton.telemedicine.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public record Disponibilidade(Long id, Long medicoId, LocalDate data, LocalTime inicio, LocalTime fim) {
}
