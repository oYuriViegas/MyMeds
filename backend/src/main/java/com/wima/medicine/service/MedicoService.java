package com.wima.medicine.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wima.medicine.models.Medico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MedicoService {

    private static final String ROOT_API = "https://api.infosimples.com/api/v2/consultas/cfm/cadastro?inscricao=%s&uf=%s&token=NdHL3eDFZIApnc3MbBOLBiltfwc3IruafkncITqf&timeout=300";

    public Medico registerByCrm(Medico medico) throws URISyntaxException, IOException, InterruptedException {
        final String crm = medico.getCrm();
        final String uf = medico.getUf();

        final String uri = String.format(ROOT_API, crm, uf);

        //usando o método HTTP do próprio java
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .headers("Content-type", "application/json")
                .GET()
                .build();


        //manda o request para a api e salva na var response
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 400) {
            return null;
        }

        String body = response.body();

        if (Objects.isNull(body)) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ValidarCrmResponse validated = mapper.readValue(body, ValidarCrmResponse.class);

        String[] fullName = validated.getData().get(0).getNome().split(" ");
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
