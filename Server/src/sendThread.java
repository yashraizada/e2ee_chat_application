import java.io.IOException;
import java.io.ObjectOutputStream;

public class sendThread{
    ObjectOutputStream outputStream;

    sendThread(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendMessage(Memo message) throws IOException {
        outputStream.writeObject(message);
        outputStream.reset();
    }
}
