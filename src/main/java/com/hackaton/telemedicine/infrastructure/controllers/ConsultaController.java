package com.hackaton.telemedicine.infrastructure.controllers;


import com.hackaton.telemedicine.application.useCases.ConsultaInteractor;
import com.hackaton.telemedicine.infrastructure.controllers.dto.ConsultaDTO;
import com.hackaton.telemedicine.infrastructure.controllers.dto.ConsultaDTOMapper;
import com.hackaton.telemedicine.infrastructure.controllers.dto.ConsultaPendenteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/consulta")
public class ConsultaController {

    private final ConsultaInteractor consultaInteractor;
    private final ConsultaDTOMapper consultaDTOMapper;

    public ConsultaController(ConsultaInteractor consultaInteractor, ConsultaDTOMapper consultaDTOMapper) {
        this.consultaInteractor = consultaInteractor;
        this.consultaDTOMapper = consultaDTOMapper;
    }

    @GetMapping(value = "/buscaPendentes")
    public ResponseEntity<List<ConsultaPendenteDTO>> buscaPendentes(@RequestParam String crm){
        List<ConsultaPendenteDTO> consultasPendentes = consultaInteractor.buscaPendentes(crm);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultasPendentes);
    }

    @PutMapping(value = "/aceitar/{id}")
    public ResponseEntity<Void> editaConsulta(@RequestParam Boolean aceitarConsulta, @PathVariable Long id) throws Exception {
        consultaInteractor.alteraStatusConsulta(id, aceitarConsulta);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/criar")
    public ResponseEntity<Void> criaConsulta(@RequestBody ConsultaDTO consultaDTO){
        consultaInteractor.criarConsulta(consultaDTOMapper.toDomain(consultaDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/editar/{id}")
    public ResponseEntity<Void> editaConsulta(@RequestBody ConsultaDTO consultaDTO, @PathVariable Long idConsulta){
        consultaInteractor.editarConsulta(consultaDTOMapper.toDomain(consultaDTO), idConsulta);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/excluir/{id}")
    public ResponseEntity<Void> excluiConsulta(@PathVariable Long idConsulta){
        consultaInteractor.excluirConsulta(idConsulta);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
