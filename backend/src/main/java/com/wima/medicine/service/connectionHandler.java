package com.wima.medicine.service;

import com.wima.medicine.models.Medico;

import java.io.IOException;
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
        ObjectOutputStream transmissor = null;
        ObjectInputStream receptor = null;
        try {
            transmissor = new ObjectOutputStream(conexao.getOutputStream());
            receptor = new ObjectInputStream(conexao.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Medico medico;
        try {
            medico = (Medico) receptor.readObject();
            System.out.println("Medico recebido");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MedicoService service = new MedicoService();
        final Medico validated = service.registerByCrm(medico);
        try {
            transmissor.writeObject(validated);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            transmissor.close();
            receptor.close();
            conexao.close();
            System.out.println("Conexao encerrada.");
        }catch(Exception ignored){}
    }
}
