package com.hackaton.telemedicine.infrastructure.gateways;




import com.hackaton.telemedicine.entities.Paciente;
import com.hackaton.telemedicine.infrastructure.persistence.PacienteEntity;


public class PacientEntityMapper {

    PacienteEntity toEntity(Paciente paciente){
        return new PacienteEntity(paciente.nome(), paciente.cpf(), paciente.phone(), paciente.email(),
                paciente.dataNascimento(), paciente.endereco());
    }

    Paciente toDomain(PacienteEntity paciente){
        return new Paciente(paciente.getNome(), paciente.getDataNascimento(), paciente.getCpf(), paciente.getPhone(),
                paciente.getEmail(), paciente.getEndereco());
    }

}
