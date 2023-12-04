
package com.wima.medicine.service;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class connectionAcceptor extends Thread {

    private ArrayList<connectionHandler> tratadoras;

    public connectionAcceptor (ArrayList<connectionHandler> tratadoras) throws Exception {
        if (tratadoras==null)
            throw new Exception ("Local para armazenar tratadoras de conexão não fornecido.");
        this.tratadoras=tratadoras;
    }

    public void run (){

        ServerSocket pedido = null;
        try {
            pedido = new ServerSocket(2424);
        } catch (IOException e) {
            System.err.println("erro");
            throw new RuntimeException(e);
        }

        for(;;){
            try {
                Socket conexao = pedido.accept();
                this.tratadoras.add(new connectionHandler(conexao));
                this.tratadoras.get(this.tratadoras.size()-1).start();
                System.out.println("Conexão aberta na porta 2424");
            } catch (Exception e) {
                System.err.println("erro");
            }
        }
    }


}