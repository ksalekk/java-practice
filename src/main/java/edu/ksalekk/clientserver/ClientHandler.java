package edu.ksalekk.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final PhoneBookServer phoneBookServer;

    public ClientHandler(Socket clientSocket, PhoneBookServer phoneBookServer) {
        this.clientSocket = clientSocket;
        this.phoneBookServer = phoneBookServer;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);

            String requestLine = br.readLine().toLowerCase();
            String[] params = requestLine.split(" ");

            if(params.length < 2 || params.length >3) {
                pw.println("Invalid request");
            }

            String requestMethod = params[0];
            String name = params[1];

            if(requestMethod.equals("put") && params.length == 3) {
                String number = params[2];
                this.phoneBookServer.put(name, number);
                pw.println(this.phoneBookServer.get(name));
            } else if(requestMethod.equals("get") && params.length == 2) {
                pw.println(this.phoneBookServer.get(name));
            } else {
                pw.println("Invalid request");
            }

            clientSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
