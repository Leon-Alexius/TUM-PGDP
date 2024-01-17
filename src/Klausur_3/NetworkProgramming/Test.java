package Klausur_3.NetworkProgramming;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);

        // Auf Verbindung warten
        Socket client =serverSocket.accept();
        serverSocket.close();

        // Nachrichten lesen
        BufferedReader rd = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String fromClient = rd.readLine();

        // Nachrichten senden
        if(fromClient.equals("Hallo Welt!")) {
            PrintWriter wr = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            wr.println("Hallo Pinguine!");
            wr.flush();
            wr.close();
        }

        // Alles schließen
        rd.close();
        client.close();
    }
}
