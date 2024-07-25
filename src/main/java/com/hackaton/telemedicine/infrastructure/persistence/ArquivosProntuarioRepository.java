package com.hackaton.telemedicine.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArquivosProntuarioRepository extends JpaRepository<ArquivosProntuarioEntity, Long> {
    List<ArquivosProntuarioEntity> findByProntuarioId(Long prontuarioId);
}
