package com.hackaton.telemedicine.entities;

import java.util.Date;

public record Prontuario(Paciente paciente, String descricao, String dadosDocumentos, Date dataCriacao) {
}
