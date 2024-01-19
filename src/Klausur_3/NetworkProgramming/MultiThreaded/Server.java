package Klausur_3.NetworkProgramming.MultiThreaded;

import java.io.*;
import java.util.concurrent.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Server {
    private static final int PORT = 1234;
    private static final int SHUTDOWN_DELAY = 60; // Shutdown delay in seconds

    /**
     * Run the server
     */
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(PORT);

            // Executor Service (Scheduled) for managing ServerThread with auto-shutdown
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ){

            // Confirm Port
            System.out.println("Server is listening on port " + PORT);

            // Schedule a task to shut down the server socket after a delay
            executor.schedule(() -> {
                try {
                    System.out.println("Shutting down server...");
                    serverSocket.close();
                }
                catch (IOException e) {
                    System.out.println("Server exception: " + e.getMessage());
                }
            }, SHUTDOWN_DELAY, TimeUnit.SECONDS);

            // Run the Server and listen to Requests
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("New client connected");

                    new ServerThread(socket).start(); // handle requests using threads
                }
                catch (SocketException e) {
                    // ServerSocket is closed
                    System.out.println("Server socket is closed. Exiting...");
                    break;
                }
            }

            executor.shutdown(); // close the executor
        }
        catch (Exception ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }

    // Handling Requests
    static class ServerThread extends Thread {
        private final Socket socket;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                String text;

                do {
                    text = reader.readLine();
                    String message = "Client " + this.getName() + " says: " + text;
                    System.out.println(message);

                } while (!text.equals("bye"));

                socket.close();

            }
            catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
            }
        }
    }
}
