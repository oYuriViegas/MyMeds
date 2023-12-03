package com.wima.medicine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wima.medicine.models.Paciente;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    private String id;
    private String nome;
    private String doctorId;

    public static PacienteDto fromEntity(Paciente entity) {
        return PacienteDto.builder()
            .id(entity.getId())
            .nome(entity.getNome())
            .doctorId(entity.getMedicoId())
            .build();
    }
}
