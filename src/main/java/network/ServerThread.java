package network;

import persistence.DAO.StoreDAO;
import persistence.DTO.*;
import service.*;

import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    private int id;
    private Socket socket;
    private IO io;

    private Protocol p;

    private UserService userService;

    public ServerThread(Socket socket) throws IOException {
        id = socket.getPort();
        this.socket = socket;

        io = new IO(socket);
        this.userService = new UserService();
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
        int packetCode = p.getCode();
        System.out.println(p);
        Object obj = null;
        if (p.getBodyLength() > 0) {
            obj = p.getBody();
        }
        Protocol protocol;
        switch(packetType) {
            case Protocol.EXIT: //-1
                System.out.println(this.id + "의 Thread 할당 종료");
                Server.remove(this.id);
                break;
            case Protocol.LOGIN_REQ:
                protocol = userService.login((UserDTO) obj);
                io.send(protocol);
                break;
        }
            /*case Protocol.REGISTER_REQ:
                switch(packetCode) {
                    case Protocol.STORE:
                        protocol = storeService.registerStore((StoreDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.MENU:
                        protocol = menuService.registerMenu((MenuDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.MENU_OPTION:
                        protocol = menuOptionService.registerMenuOption((MenuOptionDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.REVIEW_AND_RATING:
                        protocol = reviewService.registerReview((ReviewDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.ORDER:
                        protocol = ordertableService.registerOrder((OrdertableDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.APPROVE_ORDER:
                        protocol = ordertableService.approveOrder((OrdertableDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.REFUSE_ORDER:
                        protocol = ordertableService.refuseOrder((OrdertableDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.APPROVE_STORE:
                        protocol = storeService.approveStore((StoreDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.REFUSE_STORE:
                        protocol = storeService.refuseStore((StoreDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.APPROVE_MENU:
                        protocol = menuService.approveMenu((MenuDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.REFUSE_MENU:
                        protocol = menuService.refuseMenu((MenuDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.APPROVE_OWNER:
                        protocol = userService.approveOwner((UserDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.REFUSE_OWNER:
                        protocol = userService.refuseOwner((UserDTO) obj);
                        io.send(protocol);
                        break;
                }
            case Protocol.INQUIRY_REQ:
                switch(packetCode) {
                    case Protocol.ORDERCNT_PER_MENU:
                        protocol = ordertableService.inquiryOrderCntPerMenu((OrdertableDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.SALES_PER_MENU:
                        protocol = ordertableService.inquirySalesPerMenu((OrdertableDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.STORE:
                        protocol = storeService.inquiryStore((StoreDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.ORDER:
                        protocol = ordertableService.inquiryOrder((OrdertableDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.ORDER_INFO:
                        protocol = ordertableService.inquiryOrderInfo((OrdertableDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.REVIEW_AND_RATING:
                        protocol = reviewService.inquiryReviewAndRating((ReviewDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.ORDERCNT_PER_STORE:
                        protocol = ordertableService.inquiryOrderCntPerStore((OrdertableDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.SALES_PER_STORE:
                        protocol = ordertableService.inquirySalesPerStore((OrdertableDTO) obj);
                        io.send(protocol);
                        break;
                }
            case Protocol.SETTING_REQ:
                protocol = storeService.setBusinessHours((StoreDTO) obj);
                io.send(protocol);
                break;
            case Protocol.UPDATE_REQ:
                switch(packetCode) {
                    case Protocol.PRIVACY:
                        protocol = userService.updatePrivacy((UserDTO) obj);
                        io.send(protocol);
                        break;
                    case Protocol.PASSWORD:
                        protocol = userService.updatePassword((UserDTO) obj);
                        io.send(protocol);
                        break;
                }
            case Protocol.CANCEL_REQ:
                protocol = ordertableService.cancelOrder((OrdertableDTO) obj);
                io.send(protocol);
                break;
        }*/
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
