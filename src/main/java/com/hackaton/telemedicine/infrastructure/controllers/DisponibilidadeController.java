package com.hackaton.telemedicine.infrastructure.controllers;


import com.hackaton.telemedicine.application.useCases.DisponibilidadeInteractor;
import com.hackaton.telemedicine.entities.Disponibilidade;
import com.hackaton.telemedicine.infrastructure.controllers.dto.DisponibilidadeDTO;
import com.hackaton.telemedicine.infrastructure.controllers.dto.DisponibilidadeDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DisponibilidadeController {

    private final DisponibilidadeInteractor disponibilidadeInteractor;
    private final DisponibilidadeDTOMapper disponibilidadeDTOMapper;

    public DisponibilidadeController(DisponibilidadeInteractor disponibilidadeInteractor, DisponibilidadeDTOMapper disponibilidadeDTOMapper) {
        this.disponibilidadeInteractor = disponibilidadeInteractor;
        this.disponibilidadeDTOMapper = disponibilidadeDTOMapper;
    }

    @GetMapping
    public ResponseEntity<List<DisponibilidadeDTO>> getDisponibilidadesPorMedico(@RequestParam Long idMedico){
        List<Disponibilidade> disponibilidadeList = disponibilidadeInteractor.buscaDisponibilidadePeloMedicoId(idMedico);
        return ResponseEntity.ok().body(disponibilidadeDTOMapper.toDTOList(disponibilidadeList));
    }

    @PostMapping
    public ResponseEntity<Void> criaDisponibilidade(@RequestBody DisponibilidadeDTO disponibilidadeDTO){
        disponibilidadeInteractor.adicionarDisponibilidade(disponibilidadeDTOMapper.toDomain(disponibilidadeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluiDisponibilidade(@PathVariable Long id){
        disponibilidadeInteractor.excluirDisponibilidade(id);
        return ResponseEntity.ok().build();
    }
}
