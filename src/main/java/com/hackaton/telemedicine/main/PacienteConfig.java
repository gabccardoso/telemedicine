package com.hackaton.telemedicine.main;

import com.hackaton.telemedicine.application.gateways.PacienteGateway;
import com.hackaton.telemedicine.infrastructure.gateways.PacientEntityMapper;
import com.hackaton.telemedicine.infrastructure.gateways.PacientRepositoryGateway;
import com.hackaton.telemedicine.infrastructure.persistence.PacienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacienteConfig {

    @Bean
    PacienteGateway pacienteGateway(PacienteRepository pacienteRepository, PacientEntityMapper pacientEntityMapper){
        return new PacientRepositoryGateway(pacientEntityMapper, pacienteRepository);
    }

    @Bean
    PacientEntityMapper pacientEntityMapper(){
        return new PacientEntityMapper();
    }

}
