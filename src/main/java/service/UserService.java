package service;
import network.Protocol;
import persistence.DAO.*;
import persistence.DTO.*;
import persistence.MyBatisConnectionFactory;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private final UserDAO userDAO;
    Scanner sc = new Scanner(System.in);


    public UserService(UserDAO userDAO)// 의존성 주입
    {
        this.userDAO = userDAO;
    }

    public List<UserDTO> selectAll()
    {
        List<UserDTO> all = userDAO.selectAll();
        return all;
    }


    public UserService()
    {
        this.userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
    }

    public void order()
    {
        // 1. 주문할 가게 정하기
        // 2. 그 가게의 메뉴 리스트 보여줌(table 다 가져옴)
        // 3. 주문할 메뉴 번호 입력받음.
        // 4. 주문가능한 메뉴 옵션을 보여줌.
        // 5. 메뉴 옵션 번호를 입력받음.
    }

    public void insertUser(String name, String id, String pw, String nickname, String pNumber, String address, int age){
        UserDTO userDTO = new UserDTO(name, id, pw, nickname, pNumber, address, age);

        int result = userDAO.insertUser(userDTO);
        //userView.insertUser(result);
    }

    public void selectUser(){
        List<UserDTO> userDTOList = userDAO.selectAll();
        //userView.printList(userDTOList);
    }

    public Protocol login(UserDTO userDTO) {
        List<UserDTO> loginResult = userDAO.login(userDTO);

        if (loginResult.size() == 1) {
            Protocol p = new Protocol(Protocol.LOGIN_RES, Protocol.SUCCESS);
            p.setBody(loginResult.get(0));
            System.out.println(p.getBody());
            System.out.println("로그인 성공");
            return p;
        }//성공
        else {
            Protocol p = new Protocol(Protocol.LOGIN_RES, Protocol.FAIL);
            p.setBody(new UserDTO());
            System.out.println(p.getBody());
            System.out.println("로그인 실패");
            return p;
        }//실패
    }



}