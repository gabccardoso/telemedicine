package com.hackaton.telemedicine.infrastructure.persistence;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "DISPONIBILIDADE_MEDICA")
public class DisponibilidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long medicoId;
    private LocalDate data;
    private LocalTime inicio;
    private LocalTime fim;

    public DisponibilidadeEntity(Long medicoId, LocalDate data, LocalTime inicio, LocalTime fim) {
        this.medicoId = medicoId;
        this.data = data;
        this.inicio = inicio;
        this.fim = fim;
    }

    public DisponibilidadeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
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
}
