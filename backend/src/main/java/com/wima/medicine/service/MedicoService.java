package com.wima.medicine.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wima.medicine.models.Medico;
import com.wima.medicine.repositories.MedicoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class MedicoService {
    //servidor aqui
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MedicoRepository repository;

    private static final String ROOT_API = "https://api.infosimples.com/api/v2/consultas/cfm/cadastro?inscricao=%s&uf=%s&token=YGOdtOC7T8U3_OtNmKiV6W5kHBIYYPQl6LkeXwgt&timeout=300";

    public Medico registerByCrm(Medico medico) {
        final String crm = medico.getCrm();
        final String uf = medico.getUf();
        var existsDoctor = this.repository.findByCrmAndUf(crm, uf);

        if (Objects.nonNull(existsDoctor)) {
            return null;
        }

        final String uri = String.format(ROOT_API, crm, uf);
        ResponseEntity<ValidarCrmResponse> response =
                this.restTemplate.postForEntity(uri, null, ValidarCrmResponse.class);

        if (response.getStatusCode().isError()) {
            return null;
        }

        ValidarCrmResponse body = response.getBody();

        if (Objects.isNull(body)) {
            return null;
        }

        String[] fullName = body.getData().get(0).getNome().split(" ");
        String primeiroNome = Arrays.asList(fullName).get(0);
        String ultimoNome = Arrays.asList(fullName).get(fullName.length - 1);

        return Medico.builder()
                .primeiroNome(primeiroNome)
                .ultimoNome(ultimoNome)
                .crm(crm)
                .uf(uf)
                .senha(medico.getSenha())
                .build();
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class ValidarCrmResponse {
    private String code;
    private List<ValidarCrmDataResponse> data;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class ValidarCrmDataResponse {
    private String nome;

}
