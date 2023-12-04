package com.wima.medicine.service;

import com.wima.medicine.models.*;

import java.io.*;
import java.net.*;

public class Cliente {
    public static Medico main(Medico medico) {
        try {
            System.out.println("aaa");
            Socket conexao = new Socket("localhost", 2424);

            ObjectOutputStream transmissor = new ObjectOutputStream(conexao.getOutputStream());

            ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream());

            System.out.println("aaa");
            transmissor.writeObject(medico);
            transmissor.flush();
            System.out.println("Medico enviado");


            Medico validated = null;
            try {
                validated = (Medico) receptor.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return validated;
        } catch (Exception ignored) {}
        return null;
    }
}
