package service;

import persistence.MyBatisConnectionFactory;
import persistence.DAO.*;
import persistence.DTO.*;

import java.util.List;
import java.util.Scanner;

public class OwnerService {

    Scanner sc = new Scanner(System.in);
    private MenuDAO menuDAO;

    public OwnerService(){
        this.menuDAO = new MenuDAO(MyBatisConnectionFactory.getSqlSessionFactory());
    }



    //Menu
/*    public void insertMenu(){
        System.out.print("등록할 가게의 이름을 입력하십시오. : ");
        String storeName = sc.nextLine();
        int store_pk = menuDAO.selectStoreKeyByStoreName(storeName);
        System.out.print("등록할 메뉴의 이름를 입력하십시오. : ");
        String menuName = sc.nextLine();
        System.out.print("등록할 메뉴의 가격을 입력하십시오. : ");
        Integer price = sc.nextInt();
        System.out.print("등록할 메뉴의 한정수량을 입력하십시오. : ");
        int limitedQuantity = sc.nextInt();
        System.out.print("등록할 메뉴의 분류명을 입력하십시오. : ");
        String series = sc.next();

        MenuDTO menuDTO = new MenuDTO(store_pk, menuName, price, limitedQuantity, series);
        menuDAO.insertMenu(menuDTO);
        List<MenuDTO> result = menuDAO.selectMenu(menuName);
        menuView.insertResult(result);
    }*/

    /*public void changeMenuNameAndPrice(int pk,  String menuName, Integer price){
        MenuDTO menuDTO = new MenuDTO(pk,  menuName, price);

        int result = menuDAO.changeMenuNameAndPrice(menuDTO);
        menuView.insertResult(result);
    }*/

    /*public void changeMenuNameAndPrice(int pk,  String menuName){
        MenuDTO menuDTO = new MenuDTO(pk, menuName);

        int result = menuDAO.changeMenuNameAndPrice(menuDTO);
        menuView.insertResult(result);
    }*/

    /*public void changeMenuNameAndPrice(int pk, Integer price){
        MenuDTO menuDTO = new MenuDTO(pk, price);

        int result = menuDAO.changeMenuNameAndPrice(menuDTO);
        menuView.insertResult(result);
    }*/
}
