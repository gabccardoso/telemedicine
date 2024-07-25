package com.hackaton.telemedicine.infrastructure.gateways.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackaton.telemedicine.enums.Operacao;
import com.hackaton.telemedicine.application.gateways.MedicoGateway;
import com.hackaton.telemedicine.application.gateways.PacienteGateway;
import com.hackaton.telemedicine.entities.Medico;
import com.hackaton.telemedicine.entities.Paciente;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class RabbitConsumerUtil {

    private final PacienteGateway pacienteGateway;
    private final MedicoGateway medicoGateway;

    private static final String RABBITMQ_QUEUE_USUARIO_PACIENTE = "usuario-paciente";
    private static final String RABBITMQ_QUEUE_USUARIO_MEDICO = "usuario-medico";

    public RabbitConsumerUtil(PacienteGateway pacienteGateway, MedicoGateway medicoGateway) {
        this.pacienteGateway = pacienteGateway;
        this.medicoGateway = medicoGateway;
    }

    @RabbitListener(queues = RABBITMQ_QUEUE_USUARIO_PACIENTE)
    public void operacoesPaciente(String usuario) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(usuario);
        String birthDateString = jsonNode.get("dateOfBirth").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        Paciente paciente = new Paciente(jsonNode.get("name").asText(), birthDate, jsonNode.get("cpf").asText(),
                jsonNode.get("phone").asText(), jsonNode.get("email").asText(), jsonNode.get("endereco").asText());
        String operacaoRecebida = jsonNode.get("operacao").asText();
        if(operacaoRecebida.equalsIgnoreCase(Operacao.CRIAR.name())){
            pacienteGateway.createPacient(paciente);
        } else if (operacaoRecebida.equalsIgnoreCase(Operacao.ATUALIZAR.name())){
            pacienteGateway.updatePacient(paciente);
        } else if (operacaoRecebida.equalsIgnoreCase(Operacao.EXCLUIR.name())){
            pacienteGateway.deletePacient(paciente.cpf());
        }
    }

    @RabbitListener(queues = RABBITMQ_QUEUE_USUARIO_MEDICO)
    public void operacoesMedico(String usuario) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(usuario);
        String birthDateString = jsonNode.get("dateOfBirth").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        Medico medico = new Medico(jsonNode.get("name").asText(), birthDate, jsonNode.get("crm").asText(),
                jsonNode.get("phone").asText(), jsonNode.get("email").asText(), jsonNode.get("endereco").asText(),
                jsonNode.get("especialidade").asText());
        String operacaoRecebida = jsonNode.get("operacao").asText();
        if(operacaoRecebida.equalsIgnoreCase(Operacao.CRIAR.name())){
            medicoGateway.createDoctor(medico);
        } else if (operacaoRecebida.equalsIgnoreCase(Operacao.ATUALIZAR.name())){
            medicoGateway.updateDoctor(medico);
        } else if (operacaoRecebida.equalsIgnoreCase(Operacao.EXCLUIR.name())){
            medicoGateway.deleteDoctor(medico.crm());
        }
    }


}
