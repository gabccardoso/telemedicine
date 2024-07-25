package com.hackaton.telemedicine.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    PacienteEntity findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
