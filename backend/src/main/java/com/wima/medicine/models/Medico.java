package com.wima.medicine.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

//lombok
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "medicos")
public class Medico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String senha;
    private String primeiroNome;
    private String ultimoNome;
    private String crm;
    private String uf;

}
