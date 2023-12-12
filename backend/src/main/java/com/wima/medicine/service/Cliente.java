package com.wima.medicine.service;

import com.wima.medicine.models.*;

import java.io.*;
import java.net.*;
//isso deveria ser o frontend
public class Cliente {
    public static Medico main(Medico medico) {
        try {
            Socket conexao = new Socket("localhost", 2424);

            ObjectOutputStream transmissor = new ObjectOutputStream(conexao.getOutputStream());

            ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream());

            transmissor.writeObject(medico);
            transmissor.flush();
            System.out.println("Medico enviado ao servidor");

            try {
                return (Medico) receptor.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception ignored) {
            return null;
        }
    }
}
