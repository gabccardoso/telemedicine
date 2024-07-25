package com.hackaton.telemedicine.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public record Consulta(String crm, String cpf, LocalDate data, LocalTime inicio, LocalTime fim){}


