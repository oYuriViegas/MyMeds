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
public class MedicoLoginDto {
    private String crm;
    private String senha;
}
