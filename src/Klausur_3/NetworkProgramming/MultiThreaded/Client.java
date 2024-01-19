package Klausur_3.NetworkProgramming.MultiThreaded;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "localhost"; // ex. 127.0.0.1
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket(SERVER_IP, SERVER_PORT);) {
            // Prepare the PrintWriter
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            // Send messages to server (autoFlush)
            writer.println("Hello, Server!");
            writer.println("How are you?");
            writer.println("bye");

            // socket.close(); // redundant
        }
        catch (IOException ioException){
            System.out.println("Error! Cause: {" + ioException.getMessage() + "}");
        }
    }
}
