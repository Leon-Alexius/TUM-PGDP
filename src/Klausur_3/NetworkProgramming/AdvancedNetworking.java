package Klausur_3.NetworkProgramming;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AdvancedNetworking {
    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    /**
     * Standard Constructor
     */
    protected AdvancedNetworking(int port){
        try {
            this.serverSocket = new ServerSocket(port); // https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html
            this.clientSocket = serverSocket.accept(); // https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Custom Constructor for Socket
     */
    protected Socket createClientSocket(String host, int port) throws IOException{
        // Constructor: Socket(String host, int port)
        // Usage: Socket("localhost", 8080)
        return new Socket(host, port);
    }

    /**
     * Read what client has sent
     */
    protected String readClientRequest(){
        // getInputStream() : Returns an input stream for this socket.
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            return bufferedReader.readLine();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Send something to Client
     */
    protected void sendMessageToClient(String message){
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            printWriter.println(message); // insert the message to Buffer
            printWriter.flush(); // send buffered Data
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Example why you shouldn't close any Reader or Writer -> Read AboutSocket.md
     */
    protected void closeConnection(Socket socket){
        try {
            bufferedReader.close(); // close the BufferedReader -> InputStreamReader -> InputStream -> Socket
            printWriter.close(); // close the PrintWriter -> OutputStreamWriter -> OutputStream -> Socket
            socket.close(); // Close the Socket and effectively close the associated Output-/InputStream (redundant)
        }
        catch (IOException e) {
                throw new RuntimeException(e);
        }
    }
}
