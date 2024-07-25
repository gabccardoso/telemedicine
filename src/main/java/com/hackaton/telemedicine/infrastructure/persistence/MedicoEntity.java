package com.hackaton.telemedicine.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "MEDICO")
@Entity
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;
    private String phone;
    private String email;
    private LocalDate dataNascimento;
    private String especialidade;
    private String endereco;

    public MedicoEntity(String nome, String crm, String phone, String email, LocalDate dataNascimento, String especialidade, String endereco) {
        this.nome = nome;
        this.crm = crm;
        this.phone = phone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }

    public MedicoEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
