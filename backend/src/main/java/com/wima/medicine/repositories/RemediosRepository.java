package com.wima.medicine.repositories;

import com.wima.medicine.models.Remedios;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RemediosRepository extends MongoRepository<Remedios, String> {

    public List<Remedios> findByPacienteId(String pacienteId);

}
