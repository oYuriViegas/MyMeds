
package com.wima.medicine.server;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class connectionAcceptor extends Thread {

    private ArrayList<connectionHandler> tratadoras;

    public connectionAcceptor (ArrayList<connectionHandler> tratadoras) throws Exception {
        if (tratadoras==null)
            throw new Exception ("Local para armazenar tratadoras de conexao não fornecido.");
        this.tratadoras=tratadoras;
    }

    public void run (){

        ServerSocket pedido = null;
        try {
            pedido = new ServerSocket(69420);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(;;){
            try {
                Socket conexao = pedido.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}