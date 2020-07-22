import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class clientEventManager {

    clientEventManager(ObjectOutputStream outputStream, ObjectInputStream inputStream){
        Thread send = new Thread(new sendThread(outputStream, inputStream));
        Thread receive = new Thread(new receiveThread(outputStream, inputStream));

        send.start();
        receive.start();
    }

}
