package com.hackaton.telemedicine.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<ProntuarioEntity, Long> {
    ProntuarioEntity findByPacienteId(Long pacienteId);
}
