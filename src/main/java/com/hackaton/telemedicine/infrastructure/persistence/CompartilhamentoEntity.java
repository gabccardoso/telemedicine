package com.hackaton.telemedicine.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "COMPARTILHAMENTO")
public class CompartilhamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String protocolo;

    private String senha;

    private LocalDateTime dataExpiracao;

    @ManyToOne
    @JoinColumn(name = "prontuario_id")
    private ProntuarioEntity prontuario;

    public CompartilhamentoEntity(String protocolo, String senha, LocalDateTime dataExpiracao, ProntuarioEntity prontuario) {
        this.protocolo = protocolo;
        this.senha = senha;
        this.dataExpiracao = dataExpiracao;
        this.prontuario = prontuario;
    }

    public CompartilhamentoEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDateTime dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public ProntuarioEntity getProntuario() {
        return prontuario;
    }

    public void setProntuario(ProntuarioEntity prontuario) {
        this.prontuario = prontuario;
    }
}
