package com.wima.medicine.service;

import com.wima.medicine.models.Medico;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class connectionHandler extends Thread{

    private Socket conexao;

    public connectionHandler (Socket conexao) throws Exception {
        if (conexao==null) throw new Exception ("Conexão não fornecida");
        this.conexao=conexao;
    }
    public void run() {
        try {
            ObjectOutputStream transmissor = new ObjectOutputStream(conexao.getOutputStream());
            ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream());

            Medico medico;
            medico = (Medico) receptor.readObject();
            System.out.println("Medico recebido no servidor");

            MedicoService service = new MedicoService();
            final Medico validated = service.registerByCrm(medico);
            System.out.println("Consulta iniciada!");
            transmissor.writeObject(validated);

            transmissor.close();
            receptor.close();
            conexao.close();
            System.out.println("Conexao encerrada.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
