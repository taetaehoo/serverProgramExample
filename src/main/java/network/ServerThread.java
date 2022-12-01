package network;

import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    private int id;
    private Socket socket;
    private IO io;

    private Protocol p;

    public ServerThread(Socket socket) throws IOException {
        id = socket.getPort();
        this.socket = socket;

        io = new IO(socket);
    }
    public int getID() {
        return id;
    }
    @Override
    public void run() {
        while(true) {
            try {
                p = io.read();
                System.out.println(this.id + " request protocol number " + (int)p.getType());
                handle(p);
                System.out.println(this.id + "'s request is complete: " + (int)p.getType());
            }catch (Exception e) {
                this.stop();
            }
        }
    }

    public void handle(Protocol p) throws Exception {
        int packetType = p.getType();
        System.out.println(p);

        switch(packetType) {
            case Protocol.EXIT:
                System.out.println(this.id + "의 Thread 할당 종료");
                Server.remove(this.id);
                break;
        }
    }

    public IO getIO() {
        return io;
    }

    public void setIO(IO io) {
        this.io = io;
    }


    public void setId(int id) {
        this.id = id;
    }

}
