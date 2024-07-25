package com.hackaton.telemedicine.main;

import com.hackaton.telemedicine.application.gateways.DisponibilidadeGateway;
import com.hackaton.telemedicine.application.useCases.DisponibilidadeInteractor;
import com.hackaton.telemedicine.infrastructure.controllers.dto.DisponibilidadeDTOMapper;
import com.hackaton.telemedicine.infrastructure.gateways.DisponibilidadeEntityMapper;
import com.hackaton.telemedicine.infrastructure.gateways.DisponibilidadeRepositoryGateway;
import com.hackaton.telemedicine.infrastructure.persistence.DisponibilidadeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Disponibilidade {

    @Bean
    DisponibilidadeInteractor disponibilidadeInteractor(DisponibilidadeGateway disponibilidadeGateway){
        return new DisponibilidadeInteractor(disponibilidadeGateway);
    }

    @Bean
    DisponibilidadeGateway disponibilidadeGateway(DisponibilidadeEntityMapper disponibilidadeEntityMapper,
                                                  DisponibilidadeRepository disponibilidadeRepository){
        return new DisponibilidadeRepositoryGateway(disponibilidadeEntityMapper, disponibilidadeRepository);
    }

    @Bean
    DisponibilidadeEntityMapper disponibilidadeEntityMapper(){
        return new DisponibilidadeEntityMapper();
    }

    @Bean
    DisponibilidadeDTOMapper disponibilidadeDTOMapper(){
        return new DisponibilidadeDTOMapper();
    }
}

