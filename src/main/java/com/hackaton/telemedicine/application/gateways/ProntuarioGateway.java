package com.hackaton.telemedicine.application.gateways;

import com.hackaton.telemedicine.entities.ArquivosProntuario;
import com.hackaton.telemedicine.entities.Prontuario;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProntuarioGateway {

    Prontuario obterProntuario (Long pacienteId);

    void uploadArquivo (Long prontuarioId, MultipartFile file) throws IOException;
}
