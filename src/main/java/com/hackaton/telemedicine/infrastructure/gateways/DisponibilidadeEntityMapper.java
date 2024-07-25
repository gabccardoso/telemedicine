package com.hackaton.telemedicine.infrastructure.gateways;

import com.hackaton.telemedicine.entities.Disponibilidade;
import com.hackaton.telemedicine.infrastructure.persistence.DisponibilidadeEntity;

import java.util.ArrayList;
import java.util.List;

public class DisponibilidadeEntityMapper {

    List<Disponibilidade> toDomainList(List<DisponibilidadeEntity> disponibilidadeEntityList){
        List<Disponibilidade> disponibilidades = new ArrayList<>();
        for(DisponibilidadeEntity disponibilidade : disponibilidadeEntityList){
            disponibilidades.add(new Disponibilidade(disponibilidade.getId(), disponibilidade.getMedicoId(),
                    disponibilidade.getData(), disponibilidade.getInicio(), disponibilidade.getFim()));
        }
        return disponibilidades;
    }

    DisponibilidadeEntity toEntity (Disponibilidade disponibilidade){
        return new DisponibilidadeEntity(disponibilidade.medicoId(), disponibilidade.data(),
                disponibilidade.inicio(), disponibilidade.fim());
    }
}
