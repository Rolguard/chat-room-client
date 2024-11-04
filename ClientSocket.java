import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientSocket(String ip, int port) {
        // Connects to the socket at the given ip and port.
        try {
            clientSocket = new Socket(ip, port);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Unhandled exception type UnknownHostException
        // Unhandled exception type IOException
    }
    public String sendMessage(String message) {
        try {
            output.println(message);
            String response = input.readLine();
            return response;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return "IOException occurred: sendMessage() failed to receive response from server";
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Exception occured";
        }
        
    }
    public void close() {
        try {
            clientSocket.close();
            input.close();
            output.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        // Chat room client that takes connections
        // Join and a string
        // Leave and a string
        // Send message string (name of the room): Message
        // HTTP connections 

        // Test server-client web socket implementation
        try {
            // Need to run server beforehand separaretly, code assumes server is already started
            InetAddress internetAddress = InetAddress.getLocalHost();
            ClientSocket client = new ClientSocket(internetAddress.getHostAddress(), 8080);
            String serverResponse = client.sendMessage("Pinging server...");
            System.out.println(serverResponse);
        }
        catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
