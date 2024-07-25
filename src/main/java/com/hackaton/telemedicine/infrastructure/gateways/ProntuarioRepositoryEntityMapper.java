package com.hackaton.telemedicine.infrastructure.gateways;

import com.hackaton.telemedicine.entities.ArquivosProntuario;
import com.hackaton.telemedicine.entities.Prontuario;
import com.hackaton.telemedicine.infrastructure.persistence.ArquivosProntuarioEntity;
import com.hackaton.telemedicine.infrastructure.persistence.ProntuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class ProntuarioRepositoryEntityMapper {

    public Prontuario toDomain (ProntuarioEntity prontuarioEntity){
        return new Prontuario(prontuarioEntity.getPaciente(), toDomainList(prontuarioEntity.getArquivos()));
    }

    public List<ArquivosProntuario> toDomainList (List<ArquivosProntuarioEntity> arquivosProntuarioEntityList){
        List<ArquivosProntuario> arquivosProntuarioList = new ArrayList<>();
        for(ArquivosProntuarioEntity arquivo : arquivosProntuarioEntityList){
            arquivosProntuarioList.add(new ArquivosProntuario(arquivo.getNomeArquivo(), arquivo.getUrlArquivo(),
                    arquivo.getDataUpload()));
        }
        return  arquivosProntuarioList;
    }
}
