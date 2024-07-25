package com.hackaton.telemedicine.main;

import com.hackaton.user.application.gateways.PacientGateway;
import com.hackaton.user.application.useCases.PacientInteractor;
import com.hackaton.user.infrastructure.controllers.dto.PacientDTOMapper;
import com.hackaton.user.infrastructure.gateways.PacientEntityMapper;
import com.hackaton.user.infrastructure.gateways.PacientRepositoryGateway;
import com.hackaton.user.infrastructure.persistence.PacientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacientConfig {

    @Bean
    PacientInteractor pacientInteractor (PacientGateway pacientGateway){
        return new PacientInteractor(pacientGateway);
    }

    @Bean
    PacientGateway pacientGateway (PacientRepository pacientRepository, PacientEntityMapper pacientEntityMapper){
        return new PacientRepositoryGateway(pacientRepository, pacientEntityMapper);
    }

    @Bean
    PacientEntityMapper pacientEntityMapper(){
        return new PacientEntityMapper();
    }

    @Bean
    PacientDTOMapper pacientDTOMapper(){
        return new PacientDTOMapper();
    }
}
