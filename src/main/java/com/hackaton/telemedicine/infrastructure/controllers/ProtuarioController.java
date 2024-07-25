package com.hackaton.telemedicine.infrastructure.controllers;

import com.hackaton.telemedicine.application.useCases.ProntuarioInteractor;
import com.hackaton.telemedicine.entities.Prontuario;
import com.hackaton.telemedicine.infrastructure.controllers.dto.ProntuarioDTO;
import com.hackaton.telemedicine.infrastructure.controllers.dto.ProntuarioDTOMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/prontuario")
public class ProtuarioController {

    private final ProntuarioInteractor prontuarioInteractor;
    private final ProntuarioDTOMapper prontuarioDTOMapper;

    public ProtuarioController(ProntuarioInteractor prontuarioInteractor, ProntuarioDTOMapper prontuarioDTOMapper) {
        this.prontuarioInteractor = prontuarioInteractor;
        this.prontuarioDTOMapper = prontuarioDTOMapper;
    }

    @GetMapping(value = "/acessar/{idPaciente}")
    public ProntuarioDTO acessaProntuario(@RequestParam Long idPaciente){
        Prontuario prontuario = prontuarioInteractor.obterProntuario(idPaciente);
        return prontuarioDTOMapper.toDto(prontuario);
    }

    @PostMapping(value = "/upload/{idProntuario}")
    public void uploadDocumento(@RequestParam MultipartFile file, @PathVariable Long idProntuario) throws IOException {
        prontuarioInteractor.uploadArquivo(idProntuario, file);
    }


}
