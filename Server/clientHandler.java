import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class clientHandler extends Server implements Runnable{
    private final Socket client;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    private sendThread sendToClient;
    private receiveThread receiveFromClient;

    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    public clientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        outputStream = new ObjectOutputStream(client.getOutputStream());
        inputStream = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        receiveFromClient = new receiveThread(outputStream, inputStream);

        pool.execute(receiveFromClient);
    }
    
}
