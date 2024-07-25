package com.hackaton.telemedicine.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Table(name = "CONSULTA")
@Entity
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private MedicoEntity medico;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime inicio;

    @Column(nullable = false)
    private LocalTime fim;

    @Column(name = "link_teleconsulta")
    private String linkTeleconsulta;

    @Column(name = "status", nullable = false)
    private String statusConsulta;

    public ConsultaEntity() {
    }

    public ConsultaEntity(PacienteEntity paciente, MedicoEntity medico, LocalDate data, LocalTime inicio,
                          LocalTime fim, String linkTeleconsulta, String statusConsulta) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.inicio = inicio;
        this.fim = fim;
        this.linkTeleconsulta = linkTeleconsulta;
        this.statusConsulta = statusConsulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFim() {
        return fim;
    }

    public void setFim(LocalTime fim) {
        this.fim = fim;
    }

    public String getLinkTeleconsulta() {
        return linkTeleconsulta;
    }

    public void setLinkTeleconsulta(String linkTeleconsulta) {
        this.linkTeleconsulta = linkTeleconsulta;
    }

    public String getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(String statusConsulta) {
        this.statusConsulta = statusConsulta;
    }
}
