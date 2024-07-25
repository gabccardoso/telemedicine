package com.hackaton.telemedicine.infrastructure.gateways;

import com.hackaton.telemedicine.application.gateways.DisponibilidadeGateway;
import com.hackaton.telemedicine.entities.Disponibilidade;
import com.hackaton.telemedicine.infrastructure.persistence.DisponibilidadeEntity;
import com.hackaton.telemedicine.infrastructure.persistence.DisponibilidadeRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class DisponibilidadeRepositoryGateway implements DisponibilidadeGateway {

    private final DisponibilidadeEntityMapper disponibilidadeEntityMapper;
    private final DisponibilidadeRepository disponibilidadeRepository;

    public DisponibilidadeRepositoryGateway(DisponibilidadeEntityMapper disponibilidadeEntityMapper, DisponibilidadeRepository disponibilidadeRepository) {
        this.disponibilidadeEntityMapper = disponibilidadeEntityMapper;
        this.disponibilidadeRepository = disponibilidadeRepository;
    }


    @Override
    public void adicionarDisponibilidade(Disponibilidade disponibilidade) {
        disponibilidadeRepository.save(disponibilidadeEntityMapper.toEntity(disponibilidade));
    }

    @Override
    public void excluirDisponibilidade(Long disponibilidadeId) {
        disponibilidadeRepository.deleteById(disponibilidadeId);
    }

    @Override
    public List<Disponibilidade> buscaDisponibilidadePeloMedicoId(Long medicoId) {
        List<DisponibilidadeEntity> disponibilidadeEntityList = disponibilidadeRepository.findByMedicoId(medicoId)
                .orElseThrow(() -> new EntityNotFoundException("Disponibilidade não encontrada"));
        return disponibilidadeEntityMapper.toDomainList(disponibilidadeEntityList);

    }
}
