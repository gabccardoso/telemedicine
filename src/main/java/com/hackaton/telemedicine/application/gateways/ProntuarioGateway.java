package com.hackaton.telemedicine.application.gateways;

import com.hackaton.telemedicine.entities.ArquivosProntuario;
import com.hackaton.telemedicine.entities.Prontuario;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProntuarioGateway {

    Prontuario obterProntuario (Long pacienteId);

    void uploadArquivo (Long prontuarioId, MultipartFile file) throws IOException;

    void compartilhaArquivos(List<Long> documentosId, int tempoExpira, String emailDestinatario, Long prontuarioId);
}
