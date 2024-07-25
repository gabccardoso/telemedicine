package com.hackaton.telemedicine.application.useCases;

import com.hackaton.telemedicine.application.gateways.DisponibilidadeGateway;
import com.hackaton.telemedicine.entities.Disponibilidade;

import java.util.List;

public class DisponibilidadeInteractor {

    private final DisponibilidadeGateway disponibilidadeGateway;

    public DisponibilidadeInteractor(DisponibilidadeGateway disponibilidadeGateway) {
        this.disponibilidadeGateway = disponibilidadeGateway;
    }

    public void adicionarDisponibilidade(Disponibilidade disponibilidade){
        disponibilidadeGateway.adicionarDisponibilidade(disponibilidade);
    }

    public void excluirDisponibilidade(Long disponibilidadeId){
        disponibilidadeGateway.excluirDisponibilidade(disponibilidadeId);
    }

    public List<Disponibilidade> buscaDisponibilidadePeloMedicoId(Long medicoId){
        return disponibilidadeGateway.buscaDisponibilidadePeloMedicoId(medicoId);
    }
}
