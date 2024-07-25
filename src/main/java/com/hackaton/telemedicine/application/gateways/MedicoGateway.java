package com.hackaton.telemedicine.application.gateways;



import com.hackaton.telemedicine.entities.Medico;

import java.util.List;

public interface MedicoGateway {

    Medico createDoctor(Medico doctor);

    Medico updateDoctor(Medico doctor);

    void deleteDoctor(String crm);

}
