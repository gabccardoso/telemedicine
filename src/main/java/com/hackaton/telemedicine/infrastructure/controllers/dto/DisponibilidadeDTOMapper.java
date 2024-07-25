package com.hackaton.telemedicine.infrastructure.controllers.dto;

import com.hackaton.telemedicine.entities.Disponibilidade;

import java.util.ArrayList;
import java.util.List;

public class DisponibilidadeDTOMapper {

    public DisponibilidadeDTO toDTO(Disponibilidade disponibilidade){
        return new DisponibilidadeDTO(disponibilidade.id(), disponibilidade.medicoId(), disponibilidade.data(),
                disponibilidade.inicio(), disponibilidade.fim());
    }

    public List<DisponibilidadeDTO> toDTOList(List<Disponibilidade> disponibilidadeList){
        List<DisponibilidadeDTO> disponibilidadeDTOList = new ArrayList<>();
        for(Disponibilidade disponibilidade : disponibilidadeList){
            disponibilidadeDTOList.add(new DisponibilidadeDTO(disponibilidade.id(), disponibilidade.medicoId(), disponibilidade.data(),
                    disponibilidade.inicio(), disponibilidade.fim()));
        }
        return disponibilidadeDTOList;
    }

    public Disponibilidade toDomain(DisponibilidadeDTO dto){
        return new Disponibilidade(dto.id(), dto.medicoId(), dto.data(), dto.inicio(), dto.fim());
    }
}
