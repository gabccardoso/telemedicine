package com.hackaton.telemedicine.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    MedicoEntity findByCrm(String crm);
    void deleteByCrm(String crm);
}
