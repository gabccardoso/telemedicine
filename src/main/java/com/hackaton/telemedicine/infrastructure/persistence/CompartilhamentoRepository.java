package com.hackaton.telemedicine.infrastructure.persistence;

import org.apache.catalina.Loader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompartilhamentoRepository extends JpaRepository<CompartilhamentoEntity, Long> {
}
