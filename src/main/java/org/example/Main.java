package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ServerSocket server = null;
        try{
            server = new ServerSocket(2412);
            server.setReuseAddress(true);

            while(true){
                System.out.println();
                System.out.println("-----------------------------------------------------------");
                Socket client = server.accept();
                System.out.println("Client Connected...." + client.getInetAddress().getHostAddress());
                ClientHandler clientSock = new ClientHandler(client);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(),
                        StandardCharsets.UTF_8));
                String info = in.readLine();

                    System.out.println(info);

                new Thread(clientSock).start();
                System.out.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(server != null){
                try{
                    server.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}