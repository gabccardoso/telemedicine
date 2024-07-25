package com.hackaton.telemedicine.infrastructure.gateways;


import com.hackaton.telemedicine.application.gateways.MedicoGateway;
import com.hackaton.telemedicine.entities.Medico;
import com.hackaton.telemedicine.infrastructure.persistence.MedicoEntity;
import com.hackaton.telemedicine.infrastructure.persistence.MedicoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class MedicoRepositoryGateway implements MedicoGateway {

    private final MedicoRepository medicoRepository;
    private final MedicoEntityMapper medicoEntityMapper;

    public MedicoRepositoryGateway(MedicoRepository medicoRepository, MedicoEntityMapper medicoEntityMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoEntityMapper = medicoEntityMapper;
    }

    @Override
    public Medico createDoctor(Medico medico) {
        MedicoEntity medicoEntity = medicoRepository.findByCrm(medico.crm());
        if(medicoEntity != null) throw new EntityExistsException("CRM já cadastrado");
        medicoEntity = medicoRepository.save(medicoEntityMapper.toEntity(medico));
        return medicoEntityMapper.toDomain(medicoEntity);
    }

    @Override
    public Medico updateDoctor(Medico medico) {
        MedicoEntity medicoEntity = medicoRepository.findByCrm(medico.crm());
        if(medicoEntity == null) throw new EntityNotFoundException("Médico com CRM " + medico.crm() + "não encontrado");
        medicoEntity.setEmail(medico.email());
        medicoEntity.setNome(medico.nome());
        medicoEntity.setPhone(medico.phone());
        medicoEntity.setEndereco(medico.endereco());
        medicoEntity.setEspecialidade(medico.especialidade());
        medicoRepository.save(medicoEntity);
        return medicoEntityMapper.toDomain(medicoEntity);
    }

    @Override
    public void deleteDoctor(String crm) {
        medicoRepository.deleteByCrm(crm);
    }
}
