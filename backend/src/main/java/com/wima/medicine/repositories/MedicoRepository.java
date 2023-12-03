package com.wima.medicine.repositories;

import com.wima.medicine.models.Medico;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedicoRepository extends MongoRepository<Medico, String> {
    public Medico findByCrmAndUf(String crm, String uf);

}
