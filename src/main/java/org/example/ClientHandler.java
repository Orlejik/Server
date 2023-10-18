package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        PrintWriter out =null;
        BufferedReader in = null;

        try{
            System.out.println("Getting the outputstream form client....");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("Getting the inputstream of client");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null){
                System.out.println("Writing the received message from client");
                System.out.println("Sent from the client : " + line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                    clientSocket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
