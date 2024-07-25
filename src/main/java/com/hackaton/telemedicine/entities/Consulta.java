package com.hackaton.telemedicine.entities;

import com.hackaton.telemedicine.Enums.StatusAgendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record Consulta(String crm, String cpf, LocalDate data, LocalTime inicio, LocalTime fim){}


