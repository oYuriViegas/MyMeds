package com.wima.medicine.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class server extends Thread {

    public static void main(String[] args) {

        ArrayList<connectionHandler> tratadorasDeConexao = new ArrayList<connectionHandler>();

        try {
            new connectionAcceptor(tratadorasDeConexao).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}