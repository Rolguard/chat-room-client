import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class WebSocketServer {
    // private ServerSocket serverSocket;
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;
    private InetAddress internetAddress;

    public WebSocketServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            internetAddress = InetAddress.getLocalHost();
            System.out.println("Hostname: " + internetAddress.getHostName());
            System.out.println("Server has started on " + internetAddress.getHostAddress() + ":" + port + "\n"
            + "Waiting for a connection...");
            // serverSocket.setSoTimeout(5000);
            clientSocket = serverSocket.accept();    
            System.out.println("A client connected.");
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message = input.readLine();

            output.println("The server received your message: " + message);
            
        }
        catch (IOException e) {
            // TODO: Proper error handling for other exceptions e.g. SocketException,
            // UnknownHostException etc.
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        System.out.println("Server on " + internetAddress.getHostAddress() + ":" + port + " is closing...");
        try {
            serverSocket.close();
            clientSocket.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    public String getHostAddress() {
        return internetAddress.getHostAddress();
    }

    public int getPort() {
        return port;
    }

    public static void main(String[] args) {
        WebSocketServer server = new WebSocketServer(8080);
        server.start();
    }
}
