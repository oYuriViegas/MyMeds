package com.wima.medicine.service;

import java.util.ArrayList;

public class server extends Thread {

    public static void main() {

        ArrayList<connectionHandler> tratadorasDeConexao = new ArrayList<connectionHandler>();

        try {
            new connectionAcceptor(tratadorasDeConexao).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}