package com.hackaton.telemedicine.main;

import com.hackaton.telemedicine.application.gateways.ProntuarioGateway;
import com.hackaton.telemedicine.application.useCases.ProntuarioInteractor;
import com.hackaton.telemedicine.infrastructure.gateways.ProntuarioRepositoryEntityMapper;
import com.hackaton.telemedicine.infrastructure.gateways.ProntuarioRepositoryGateway;
import com.hackaton.telemedicine.infrastructure.gateways.utils.S3Utils;
import com.hackaton.telemedicine.infrastructure.persistence.ArquivosProntuarioRepository;
import com.hackaton.telemedicine.infrastructure.persistence.ProntuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProntuarioConfig {

    @Bean
    ProntuarioInteractor prontuarioInteractor(ProntuarioGateway prontuarioGateway){
        return new ProntuarioInteractor(prontuarioGateway);
    }

    @Bean
    ProntuarioGateway prontuarioGateway(ProntuarioRepository prontuarioRepository,
                                        ArquivosProntuarioRepository arquivosProntuarioRepository,
                                        ProntuarioRepositoryEntityMapper prontuarioRepositoryEntityMapper,
                                        S3Utils s3Utils){
        return new ProntuarioRepositoryGateway(prontuarioRepository, arquivosProntuarioRepository,
                prontuarioRepositoryEntityMapper, s3Utils);
    }

    @Bean
    ProntuarioRepositoryEntityMapper prontuarioRepositoryEntityMapper (){
        return new ProntuarioRepositoryEntityMapper();
    }
}
