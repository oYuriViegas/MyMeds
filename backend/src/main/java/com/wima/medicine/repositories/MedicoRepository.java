package com.wima.medicine.repositories;

import com.wima.medicine.models.Medico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicoRepository extends MongoRepository<Medico, String> {
    public Medico findByCrmAndUf(String crm, String uf);

    public Medico findByCrm(String crm);

    public Medico findByCrmAndSenha(String crm, String senha);
}
