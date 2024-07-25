package com.hackaton.telemedicine.main;

import com.hackaton.telemedicine.application.gateways.ConsultaGateway;
import com.hackaton.telemedicine.application.useCases.ConsultaInteractor;
import com.hackaton.telemedicine.infrastructure.gateways.ConsultaRepositoryGateway;
import com.hackaton.telemedicine.infrastructure.gateways.utils.GoogleMeetLinkGenerator;
import com.hackaton.telemedicine.infrastructure.persistence.ConsultaRepository;
import com.hackaton.telemedicine.infrastructure.persistence.DisponibilidadeRepository;
import com.hackaton.telemedicine.infrastructure.persistence.MedicoRepository;
import com.hackaton.telemedicine.infrastructure.persistence.PacienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultaConfig {

    @Bean
    ConsultaInteractor consultaInteractor (ConsultaGateway consultaGateway){
        return new ConsultaInteractor(consultaGateway);
    }

    @Bean
    ConsultaGateway consultaGateway (ConsultaRepository consultaRepository, PacienteRepository pacienteRepository,
                                     MedicoRepository medicoRepository, DisponibilidadeRepository disponibilidadeRepository,
                                     GoogleMeetLinkGenerator googleMeetLinkGenerator){
        return new ConsultaRepositoryGateway(consultaRepository, pacienteRepository, medicoRepository, disponibilidadeRepository,
                googleMeetLinkGenerator);
    }
}
