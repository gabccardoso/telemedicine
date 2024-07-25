package com.hackaton.telemedicine.infrastructure.persistence;

import com.hackaton.telemedicine.entities.Prontuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ARQUIVOS_PRONTUARIO")
public class ArquivosProntuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeArquivo;
    private String urlArquivo;
    private LocalDateTime dataUpload;

    @ManyToOne
    private ProntuarioEntity prontuario;

    public ArquivosProntuarioEntity(String nomeArquivo, String urlArquivo, LocalDateTime dataUpload, ProntuarioEntity prontuario) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.dataUpload = dataUpload;
        this.prontuario = prontuario;
    }

    public ArquivosProntuarioEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDateTime dataUpload) {
        this.dataUpload = dataUpload;
    }

    public ProntuarioEntity getProntuario() {
        return prontuario;
    }

    public void setProntuario(ProntuarioEntity prontuario) {
        this.prontuario = prontuario;
    }
}
