import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class receiveThread implements Runnable{
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    receiveThread(ObjectOutputStream outputStream, ObjectInputStream inputStream){
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    @Override
    synchronized public void run() {

    }
}
