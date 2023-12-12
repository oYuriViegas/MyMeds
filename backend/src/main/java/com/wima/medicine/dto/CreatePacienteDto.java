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
public class CreatePacienteDto {

    private String email;
    private String senha;
    private String nome;
    private String crm;

    public static Paciente.PacienteBuilder getPacienteBuilder(CreatePacienteDto entity) {
        return Paciente.builder()
                .email(entity.getEmail())
                .senha(entity.getSenha())
                .nome(entity.getNome());
    }
}
