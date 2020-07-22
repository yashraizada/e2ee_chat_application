import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 1025;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        new clientEventManager(outputStream, inputStream);
        socket.close();
    }
}
