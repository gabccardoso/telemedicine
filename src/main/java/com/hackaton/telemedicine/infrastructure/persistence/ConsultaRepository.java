package com.hackaton.telemedicine.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {
    Optional<List<ConsultaEntity>> findByMedicoAndStatusConsulta(MedicoEntity medico, String statusConsulta);
}
