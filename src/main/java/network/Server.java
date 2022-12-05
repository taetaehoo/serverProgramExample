package network;

import persistence.DAO.OrdertableDAO;
import persistence.DTO.MenuDTO;
import persistence.MyBatisConnectionFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private static ServerThread clients[];
    private static int clientCount;

    public static void main(String [] args) throws Exception {
        Server server = new Server();
        server.run();
        /*Scanner sc = new Scanner(System.in);
        System.out.println("가게 번호를 입력하시오.");
        int store_pk = sc.nextInt();
        System.out.println("메뉴 번호를 입력하시오.");
        int menu_pk = sc.nextInt();
        MenuDTO menuDTO = new MenuDTO(store_pk, menu_pk);

        OrdertableDAO ordertableDAO = new OrdertableDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ordertableDAO.insertOrder(menuDTO);*/


    }
    public Server() throws IOException {
        clients = new ServerThread[10];
        clientCount = 0;
        serverSocket = new ServerSocket(9999);
    }

    public void run() throws Exception {
        while (serverSocket != null) {
            Socket socket = serverSocket.accept();
            addThread(socket);
            System.out.println("client와 server 연결 성공");
        }
    }

    public synchronized void addThread(Socket socket) throws Exception {
        if (clientCount < clients.length) {
            clients[clientCount] = new ServerThread(socket);
            clients[clientCount].start();
            clientCount++;
        } else {
            System.out.println("할당할 수 없는 쓰레드가 없습니다.");
        }
    }

    public static int findClient(int ID) {
        for (int i = 0; i < clientCount; i++)
            if (clients[i].getID() == ID)
                return i;
        return -1;
    }

    public synchronized static void remove(int ID) throws IOException {
        int pos = findClient(ID);
        if (pos >= 0) {
            ServerThread toTerminate = clients[pos];
            if (pos < clientCount - 1)
                for (int i = pos + 1; i < clientCount; i++)
                    clients[i - 1] = clients[i];
            clientCount--;
            System.out.println(clientCount);
            toTerminate.getIO().close();
            toTerminate.stop();
        }
    }


}
