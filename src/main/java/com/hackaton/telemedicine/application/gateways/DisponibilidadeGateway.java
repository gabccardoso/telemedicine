package com.hackaton.telemedicine.application.gateways;

import com.hackaton.telemedicine.entities.Disponibilidade;

import java.util.List;

public interface DisponibilidadeGateway {

    void adicionarDisponibilidade(Disponibilidade disponibilidade);

    void excluirDisponibilidade(Long disponibilidadeId);

    List<Disponibilidade> buscaDisponibilidadePeloMedicoId(Long medicoId);
}
