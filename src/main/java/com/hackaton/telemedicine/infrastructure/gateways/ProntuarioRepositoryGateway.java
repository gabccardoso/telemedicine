package com.hackaton.telemedicine.infrastructure.gateways;

import com.hackaton.telemedicine.application.gateways.ProntuarioGateway;
import com.hackaton.telemedicine.entities.Prontuario;
import com.hackaton.telemedicine.infrastructure.gateways.utils.S3Utils;
import com.hackaton.telemedicine.infrastructure.persistence.ArquivosProntuarioEntity;
import com.hackaton.telemedicine.infrastructure.persistence.ArquivosProntuarioRepository;
import com.hackaton.telemedicine.infrastructure.persistence.ProntuarioEntity;
import com.hackaton.telemedicine.infrastructure.persistence.ProntuarioRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

public class ProntuarioRepositoryGateway implements ProntuarioGateway {

    private final ProntuarioRepository prontuarioRepository;
    private final ArquivosProntuarioRepository arquivosProntuarioRepository;
    private final ProntuarioRepositoryEntityMapper prontuarioRepositoryEntityMapper;
    private final S3Utils s3Utils;

    public ProntuarioRepositoryGateway(ProntuarioRepository prontuarioRepository, ArquivosProntuarioRepository arquivosProntuarioRepository, ProntuarioRepositoryEntityMapper prontuarioRepositoryEntityMapper, S3Utils s3Utils) {
        this.prontuarioRepository = prontuarioRepository;
        this.arquivosProntuarioRepository = arquivosProntuarioRepository;
        this.prontuarioRepositoryEntityMapper = prontuarioRepositoryEntityMapper;
        this.s3Utils = s3Utils;
    }


    @Override
    public Prontuario obterProntuario(Long pacienteId) {
        return (prontuarioRepositoryEntityMapper.toDomain(prontuarioRepository.findByPacienteId(pacienteId)));
    }

    @Override
    public void uploadArquivo(Long prontuarioId, MultipartFile file) throws IOException {
        ProntuarioEntity prontuario = prontuarioRepository.findById(prontuarioId).orElseThrow(() -> new IllegalArgumentException("Prontuário não encontrado"));

        String urlArquivo = s3Utils.uploadFile(file);

        ArquivosProntuarioEntity arquivoProntuario = new ArquivosProntuarioEntity();
        arquivoProntuario.setNomeArquivo(file.getOriginalFilename());
        arquivoProntuario.setUrlArquivo(urlArquivo);
        arquivoProntuario.setDataUpload(LocalDateTime.now());
        arquivoProntuario.setProntuario(prontuario);

        arquivosProntuarioRepository.save(arquivoProntuario);
    }


}
