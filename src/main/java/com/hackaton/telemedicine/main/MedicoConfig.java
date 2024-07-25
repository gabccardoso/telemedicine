package com.hackaton.telemedicine.main;


import com.hackaton.telemedicine.application.gateways.MedicoGateway;
import com.hackaton.telemedicine.infrastructure.gateways.MedicoEntityMapper;
import com.hackaton.telemedicine.infrastructure.gateways.MedicoRepositoryGateway;
import com.hackaton.telemedicine.infrastructure.persistence.MedicoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicoConfig {

    @Bean
    MedicoGateway medicoGateway (MedicoRepository medicoRepository, MedicoEntityMapper medicoEntityMapper){
        return new MedicoRepositoryGateway(medicoRepository, medicoEntityMapper);
    }

    @Bean
    MedicoEntityMapper medicoEntityMapper(){
        return new MedicoEntityMapper();
    }
}
