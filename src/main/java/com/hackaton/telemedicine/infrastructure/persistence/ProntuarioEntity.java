package com.hackaton.telemedicine.infrastructure.persistence;

import com.hackaton.telemedicine.entities.Paciente;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRONTUARIO")
public class ProntuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL)
    private List<ArquivosProntuarioEntity> arquivos;

    public ProntuarioEntity() {
    }

    public ProntuarioEntity(Paciente paciente, List<ArquivosProntuarioEntity> arquivos) {
        this.paciente = paciente;
        this.arquivos = arquivos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<ArquivosProntuarioEntity> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<ArquivosProntuarioEntity> arquivos) {
        this.arquivos = arquivos;
    }
}
