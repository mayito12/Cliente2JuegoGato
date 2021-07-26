package main.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadClient extends Observable implements Runnable{
    private Socket socket;
    private DataInputStream bufferDeEntrada = null;

    public ThreadClient(Socket socket) {
        this.socket = socket;
    }
    public void run() {

        try {
            bufferDeEntrada = new DataInputStream(socket.getInputStream());
            String mensaje = "";
            do {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000L)+100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    mensaje = bufferDeEntrada.readUTF();
                    String[] datagrama;
                    datagrama = mensaje.split(":");
                    this.setChanged();
                    this.notifyObservers(datagrama[1]);
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }while (!mensaje.equals("FIN"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
