package com.wima.medicine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wima.medicine.models.Medico;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicoDto {
    private String id;
    private String primeiroNome;
    private String ultimoNome;
    private String crm;
    private String uf;

    public static MedicoDto fromEntity(Medico entity) {
        return MedicoDto.builder()
            .id(entity.getId())
            .primeiroNome(entity.getPrimeiroNome())
            .ultimoNome(entity.getUltimoNome())
            .crm(entity.getCrm())
            .uf(entity.getUf())
            .build();
    }
}
