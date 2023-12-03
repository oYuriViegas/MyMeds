package com.wima.medicine.server;

import com.wima.medicine.models.Medico;
import com.wima.medicine.service.*;

import java.io.*;
import java.net.*;

public class connectionHandler extends Thread{

    private Socket conexao;
    private Medico medico;

    ObjectOutputStream transmissor = new ObjectOutputStream(conexao.getOutputStream());
    ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream());

    public connectionHandler (Socket conexao) throws Exception {
        if (conexao==null) throw new Exception ("Conexão não fornecida");
        this.conexao=conexao;
    }
    public void run() {
        try {
            this.medico= (Medico) receptor.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final Medico validated;
        validated = this.medicoService.registerByCrm(medico);
        try {
            transmissor.writeObject(validated);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
