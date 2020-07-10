import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int serverPort = 1025;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    protected static Dictionary registeredClientsOnlineStatus = new Hashtable();
    protected ArrayList<clientHandler> onlineClients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(serverPort);

        while(true){
            System.out.println("[SERVER] Waiting for client connection");
            Socket client = listener.accept();
            System.out.println("[SERVER] Connected to client");

            clientHandler clientThread = new clientHandler(client);

            pool.execute(clientThread);

        }
    }
}
