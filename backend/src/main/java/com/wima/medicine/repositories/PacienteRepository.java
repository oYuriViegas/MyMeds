package com.wima.medicine.repositories;

import com.wima.medicine.models.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PacienteRepository extends MongoRepository<Paciente, String> {

    public List<Paciente> findByMedicoId(String medicoId);
    public Paciente findByEmail(String email);
}
