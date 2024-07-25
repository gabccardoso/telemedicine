package com.hackaton.telemedicine.infrastructure.gateways;

import com.hackaton.telemedicine.application.gateways.ProntuarioGateway;
import com.hackaton.telemedicine.entities.Prontuario;
import com.hackaton.telemedicine.infrastructure.gateways.utils.EmailUtils;
import com.hackaton.telemedicine.infrastructure.gateways.utils.S3Utils;
import com.hackaton.telemedicine.infrastructure.persistence.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProntuarioRepositoryGateway implements ProntuarioGateway {

    private final ProntuarioRepository prontuarioRepository;
    private final ArquivosProntuarioRepository arquivosProntuarioRepository;
    private final ProntuarioRepositoryEntityMapper prontuarioRepositoryEntityMapper;
    private final CompartilhamentoRepository compartilhamentoRepository;
    private final S3Utils s3Utils;
    private final EmailUtils emailUtils;

    public ProntuarioRepositoryGateway(ProntuarioRepository prontuarioRepository, ArquivosProntuarioRepository arquivosProntuarioRepository, ProntuarioRepositoryEntityMapper prontuarioRepositoryEntityMapper, CompartilhamentoRepository compartilhamentoRepository, S3Utils s3Utils, EmailUtils emailUtils) {
        this.prontuarioRepository = prontuarioRepository;
        this.arquivosProntuarioRepository = arquivosProntuarioRepository;
        this.prontuarioRepositoryEntityMapper = prontuarioRepositoryEntityMapper;
        this.compartilhamentoRepository = compartilhamentoRepository;
        this.s3Utils = s3Utils;
        this.emailUtils = emailUtils;
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

    @Override
    public void compartilhaArquivos(List<Long> documentosId, int tempoExpira, String emailDestinatario, Long prontuarioId) {
        ProntuarioEntity prontuario = prontuarioRepository.findById(prontuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Prontuário não encontrado"));

        // Gera protocolo e senha
        String protocolo = UUID.randomUUID().toString();
        String senha = RandomStringUtils.randomAlphanumeric(8);

        // Define data de expiração
        LocalDateTime dataExpiracao = LocalDateTime.now().plusHours(tempoExpira);

        // Cria e salva a entidade de compartilhamento
        CompartilhamentoEntity compartilhamento = new CompartilhamentoEntity();
        compartilhamento.setProtocolo(protocolo);
        compartilhamento.setSenha(senha);
        compartilhamento.setDataExpiracao(dataExpiracao);
        compartilhamento.setProntuario(prontuario);
        compartilhamentoRepository.save(compartilhamento);

        // Envia e-mail com protocolo e senha
        emailUtils.enviarEmailCompartilhamento(emailDestinatario, protocolo, senha, dataExpiracao);

    }


}
