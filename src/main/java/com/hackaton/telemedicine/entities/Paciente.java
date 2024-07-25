package com.hackaton.telemedicine.entities;

import java.time.LocalDate;

public record Paciente(String nome, LocalDate dataNascimento, String cpf,
                          String phone, String email, String endereco ){}


