package com.hackaton.telemedicine.entities;

import java.time.LocalDate;

public record Medico(String nome, LocalDate dataNascimento, String crm,
                     String phone, String email, String endereco, String especialidade){}


