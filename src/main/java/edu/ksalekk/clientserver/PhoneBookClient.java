package edu.ksalekk.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class PhoneBookClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 32563);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            System.out.println("[C] Type your request: ");

            String request = scanner.nextLine();
            pw.println(request);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(br.readLine());

            socket.close();
        } catch (IOException e) {
            System.err.println("[C] Client error: " + e);
        }
    }
}
