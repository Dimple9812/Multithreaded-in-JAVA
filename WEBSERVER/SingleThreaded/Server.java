import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    
    public void run() throws IOException, UnknownHostException{
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(20000);
        while(true){
            try{
            System.out.println("Server is listening on port: "+port);
            Socket acceptedConnection = socket.accept();
            System.out.println("Connected to "+acceptedConnection.getRemoteSocketAddress());
            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
            toClient.println("Hello World from the server");
            toClient.close();
            fromClient.close();
            acceptedConnection.close();   
            }catch(Exception ex){
            ex.printStackTrace();     
            }
    }
}

    public static void main(String[] args){
        try{
            Server server = new Server();
            server.run();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}