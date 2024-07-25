package com.hackaton.telemedicine.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface DisponibilidadeRepository extends JpaRepository<DisponibilidadeEntity, Long> {
    Optional<List<DisponibilidadeEntity>> findByMedicoId (Long medicoId);
    Optional<DisponibilidadeEntity> findBymedicoIdAndDataAndHorario(Long medicoId, LocalDate data, LocalTime inicio, LocalTime fim);
}
