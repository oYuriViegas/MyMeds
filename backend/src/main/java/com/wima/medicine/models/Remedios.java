package com.wima.medicine.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "remedios")
public class Remedios {

    @Id
    private String id;
    private String name;
    private String pacienteId;
    private String miligramagem;
    private String intervalo;
    private String frequencia;

}
