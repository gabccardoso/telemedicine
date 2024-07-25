package com.hackaton.telemedicine.application.useCases;

import com.hackaton.telemedicine.application.gateways.ProntuarioGateway;
import com.hackaton.telemedicine.entities.Prontuario;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ProntuarioInteractor {

    private final ProntuarioGateway prontuarioGateway;

    public ProntuarioInteractor(ProntuarioGateway prontuarioGateway) {
        this.prontuarioGateway = prontuarioGateway;
    }

    public Prontuario obterProntuario (Long pacienteId){
        return prontuarioGateway.obterProntuario(pacienteId);
    }

    public void uploadArquivo (Long prontuarioId, MultipartFile file) throws IOException {
        prontuarioGateway.uploadArquivo(prontuarioId, file);
    }
}
