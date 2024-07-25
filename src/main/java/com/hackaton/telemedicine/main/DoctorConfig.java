package com.hackaton.telemedicine.main;

import com.hackaton.user.application.gateways.DoctorGateway;
import com.hackaton.user.application.useCases.DoctorInteractor;
import com.hackaton.user.infrastructure.controllers.dto.DoctorDTOMapper;
import com.hackaton.user.infrastructure.gateways.DoctorEntityMapper;
import com.hackaton.user.infrastructure.gateways.DoctorRepositoryGateway;
import com.hackaton.user.infrastructure.persistence.DoctorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorConfig {

    @Bean
    DoctorInteractor doctorInteractor (DoctorGateway doctorGateway){
        return new DoctorInteractor(doctorGateway);
    }

    @Bean
    DoctorGateway doctorGateway (DoctorRepository doctorRepository, DoctorEntityMapper doctorEntityMapper){
        return new DoctorRepositoryGateway(doctorRepository, doctorEntityMapper);
    }

    @Bean
    DoctorEntityMapper doctorEntityMapper(){return new DoctorEntityMapper();}

    @Bean
    DoctorDTOMapper doctorDTOMapper(){return new DoctorDTOMapper();}
}
