package com.hackaton.telemedicine.entities;

import java.util.List;

public record Prontuario(Paciente paciente, List<ArquivosProntuario> arquivosProntuarioList) {
}
