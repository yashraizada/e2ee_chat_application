package chatapp;

import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class clientHandler extends Server implements Runnable{
    private final Socket client;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

        private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public clientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        receiveThread rThread = new receiveThread(out, in);

        // First message from the Client to deduce the ID
        try {
            Message inCID = (Message) in.readObject();
            registeredClientsOnlineStatus.put(inCID.getSenderID(), out);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        pool.execute(rThread);
    }

    public class receiveThread implements Runnable{

        ObjectOutputStream outStream;
        ObjectInputStream inStream;

        receiveThread(ObjectOutputStream out, ObjectInputStream in){
            this.outStream = out;
            this.inStream = in;
        }

        @Override
        public void run(){
            while(true){
                try {
                    Message receivedFromClient = (Message) inStream.readObject();

                    sendThread sendToClient = new sendThread((ObjectOutputStream) registeredClientsOnlineStatus.get(receivedFromClient.getReceiverID()), receivedFromClient);
                    sendToClient.run();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class sendThread implements Runnable {

        ObjectOutputStream sendOutStream;
        Message message;

        sendThread(ObjectOutputStream out, Message message){
            this.sendOutStream = out;
            this.message = message;
        }

        @Override
        public void run() {
            try {
                sendOutStream.writeObject(message);
                sendOutStream.reset();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
