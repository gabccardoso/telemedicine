package com.hackaton.telemedicine.application.gateways;


import com.hackaton.telemedicine.entities.Paciente;

import java.util.List;

public interface PacienteGateway {

    Paciente createPacient(Paciente paciente);

    Paciente updatePacient(Paciente paciente);

    void deletePacient(String cpf);

}
