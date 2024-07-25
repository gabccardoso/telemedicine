package com.hackaton.telemedicine.infrastructure.gateways;

import com.hackaton.telemedicine.entities.Medico;
import com.hackaton.telemedicine.infrastructure.persistence.MedicoEntity;

public class MedicoEntityMapper {

    Medico toDomain (MedicoEntity medicoEntity) {
        return new Medico(medicoEntity.getNome(), medicoEntity.getDataNascimento(), medicoEntity.getCrm(), medicoEntity.getPhone(),
                medicoEntity.getEmail(), medicoEntity.getEndereco(), medicoEntity.getEspecialidade());
    }

    MedicoEntity toEntity (Medico medico) {
        return new MedicoEntity(medico.nome(), medico.crm() , medico.phone(), medico.email(), medico.dataNascimento(),
                medico.especialidade(), medico.endereco());
    }

}
