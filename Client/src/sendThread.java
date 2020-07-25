import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class sendThread implements Runnable{
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    sendThread(ObjectOutputStream outputStream, ObjectInputStream inputStream){
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    @Override
    synchronized public void run() {

    }
}
