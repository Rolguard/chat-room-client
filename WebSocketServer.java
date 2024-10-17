import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class WebSocketServer {
    // private ServerSocket serverSocket;
    

    public static void main(String[] args) {
        // Chat room client that takes connections
        // Join and a string
        // Leave and a string
        // Send message string (name of the room): Message
        // HTTP connections 
        try {
            ServerSocket serverSocket = new ServerSocket(80);
            InetAddress internetAddress = InetAddress.getLocalHost();
            System.out.println("Hostname: " + internetAddress.getHostName());
            System.out.println("Server has started on " + internetAddress.getHostAddress() + ":80\n"
            + "Waiting for a connection...");
            serverSocket.setSoTimeout(5000);
            Socket clientSocket = serverSocket.accept();
            // System.out.println("A client connected.");
            System.out.println("Server on " + internetAddress.getHostAddress() + ":80" + "is closing...");
            serverSocket.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
