package edu.ksalekk.clientserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PhoneBookServer implements Runnable{
    private static final int PORT_NUMBER = 32563;

    private final ConcurrentHashMap<String, String> phoneBook;
    private final ExecutorService threadsPool;

    public PhoneBookServer() {
        this.phoneBook = new ConcurrentHashMap<>();
        this.threadsPool = Executors.newFixedThreadPool(20);
    }

    public void put(String name, String number) {
        this.phoneBook.put(name, number);
    }

    public String get(String name) {
        if(this.phoneBook.containsKey(name)) {
            return "[S] " + name + " " + this.phoneBook.get(name);
        } else {
            return "[S] --- no name: " + name + " ---";
        }
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            System.out.println("[S] Server running");
            while(true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("[S] New connection with " + clientSocket);
                    threadsPool.submit(new ClientHandler(clientSocket, this));

                } catch (IOException e) {
                    System.err.println("[S] Server error: " + e);
                }
            }
        } catch (IOException e) {
            System.err.println("[S] Server error: " + e);
        }
    }

    public static void main(String[] args) {
        Thread phoneBookServer = new Thread(new PhoneBookServer());
        phoneBookServer.start();
    }
}
