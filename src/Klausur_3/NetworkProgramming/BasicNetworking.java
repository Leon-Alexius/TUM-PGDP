package Klausur_3.NetworkProgramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server Socket assigns a Socket to each Connection, multiple Connection can connect to the same Port
 * <ul>
 *     <li>Server Socket = Harbor Manager</li>
 *     <li>Client Socket (Socket) = different Companies</li>
 *     <li>Port = Harbor Port</li>
 *     <li>Connection/ Requests/ Data Packages = Ships</li>
 * </ul>
 */
public class BasicNetworking {
    // https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html
    // https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html
    private final int port;
    private final ServerSocket serverSocket;
    private final Socket clientSocket;

    /**
     * Create a ServerSocket that listens to the specified port and ask it to listen once
     * @param port int
     */
    protected BasicNetworking(int port){
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
            this.clientSocket = acceptConnection();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tell the ServerSocket to expect ONE connection request, and assign a Socket to that connection
     */
    protected Socket acceptConnection() throws IOException {
        return serverSocket.accept();
    }

    /**
     * Close the ServerSocket
     */
    protected void closeServerSocket() throws IOException {
        serverSocket.close();
    }

    /**
     * Example usage of .getLocalPort() and .isClosed() of ServerSocket
     */
    protected void getInfoServerSocket(){
        int portBeingListened = serverSocket.getLocalPort();
        boolean isClosed = serverSocket.isClosed();

        System.out.println("Current Target Port is: " + portBeingListened);
        System.out.println("ServerSocket isClosed: " + isClosed);
    }
}
