package com.hackaton.telemedicine.entities;

import java.time.LocalDateTime;

public record ArquivosProntuario(String nomeArquivo, String urlArquivo, LocalDateTime dataUploaded) {
}
