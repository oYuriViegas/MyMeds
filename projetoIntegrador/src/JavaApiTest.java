// https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaApiTest
{
    public static void main(Consulta dados) throws IOException
    {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://api.infosimples.com/api/v2/consultas/cfm/cadastro");

        List<NameValuePair> params = new ArrayList<NameValuePair>(4);
        params.add(new BasicNameValuePair("inscricao", dados.getCodigo()));
        params.add(new BasicNameValuePair("uf", dados.getUf()));
        params.add(new BasicNameValuePair("token", "YGOdtOC7T8U3_OtNmKiV6W5kHBIYYPQl6LkeXwgt"));
        params.add(new BasicNameValuePair("timeout", "300"));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse httpResponse = httpclient.execute(httppost);
        HttpEntity entity = httpResponse.getEntity();
        String body = EntityUtils.toString(entity, "UTF-8");
        JSONObject response = new JSONObject(body);

        if (response.getInt("code") == 200)
        {
            System.out.println("Retorno com sucesso: \n" + response.get("data"));
        }
        else if (response.getInt("code") >= 600 && response.getInt("code") <= 799)
        {
            String mensagem = "Um erro aconteceu. Leia para saber mais:\n";
            mensagem += "Código: " + response.get("code") + " (" + response.get("code_message") + ")\n";
            if (!response.isNull("errors")) {
                mensagem += response.getJSONArray("errors").join("; ");
            }
            System.out.println(mensagem);
        }
        System.out.println("Cabeçalho da consulta:\n" + response.get("header"));
        System.out.println("URLs com arquivos de visualização (HTML/PDF):\n" + response.getJSONArray("site_receipts").join("; "));
    }
}