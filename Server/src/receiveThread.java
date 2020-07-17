import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class receiveThread implements Runnable{
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    receiveThread(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        while (true){
            try {
                Memo receivedMessage = (Memo) inputStream.readObject();
                sendThread sendToRecipient = new sendThread(outputStream);
                sendToRecipient.sendMessage(receivedMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
