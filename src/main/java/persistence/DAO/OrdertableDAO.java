package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.*;
import persistence.MyBatisConnectionFactory;

import java.util.List;
import java.util.Scanner;

public class OrdertableDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public OrdertableDAO(SqlSessionFactory sqlSessionFactory)
    {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<OrdertableDTO> selectAll() {
        List<OrdertableDTO> list = null;

        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("mapper.OrdertableMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }

    // 주문 생성
/*    public List<OrdertableDTO> createOrders(){
        List<OrdertableDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();

        createOrder(1, 1, 1, 7900, true, false, true, false);
        createOrder(1, 3, 1, 7100, true, false, false, false);
        createOrder(1, 3, 1, 7100, true, false, false, false);
        createOrder(2, 4, 1, 6100, false, false, false, true);

        return dtos;
    }*/

/*    public OrdertableDTO createOrder(int user_pk, int menu_pk, int store_pk, int total){
        MenuDAO menuDAO = new MenuDAO(sqlSessionFactory);
        OrdertableDTO ordertableDTO;
        ordertableDTO = insertOrder(user_pk, menu_pk, store_pk, total);
        SqlSession session = sqlSessionFactory.openSession();

        try{
            if(menuDAO.isSaleable(menu_pk)) {
                MenuDTO menuDTO = menuDAO.materialExhaustion(menu_pk);
                session.insert("mapper.OrdertableMapper.insertOrder", ordertableDTO);
                System.out.println("주문 생성 되었습니다.");
            }
            else {
                System.out.println("재료소진으로 인해 주문 실패하였습니다.");
            }
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
        }
        finally
        {
            session.close();
        }
        return ordertableDTO;
    }*/


  /*  public OrdertableDTO insertOrder(int user_pk, int menu_pk, int store_pk, int total)
    {
        SqlSession session = sqlSessionFactory.openSession();


        OrdertableDTO ordertableDTO = new OrdertableDTO();
        ordertableDTO.setOrdertable_user_pk(user_pk);
        ordertableDTO.setOrdertable_menu_pk(menu_pk);
        ordertableDTO.setOrdertable_store_pk(store_pk);
        ordertableDTO.setTotal(total);


        ordertableDTO.setStatus(Status.접수대기.name());

        return ordertableDTO;
    }*/

    // 주문 조회
/*    public List<OrdertableDTO> inquiryOrders(int store_pk){
        List<OrdertableDTO> OrdertableList = null;
        List<MenuOptionDTO> options = null;
        OrdertableDTO ordertableDTO = new OrdertableDTO();
        ordertableDTO.setOrdertable_user_pk(store_pk);
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println("[주문내역]");
        try {
            OrdertableList = session.selectList("mapper.OrdertableMapper.inquiryOrders", store_pk);
            options = session.selectList("mapper.MenuOptionMapper.inquiryOptionAll");
        } finally {
            session.close();
        }

        for (OrdertableDTO order: OrdertableList) {
            for(MenuOptionDTO option: options) {
                order.getOptions().add(option);
            }
        }

        return OrdertableList;
    }*/

    // 주문접수
/*    public OrdertableDTO acceptOrder(int orderPk){

        OrdertableDTO ordertableDTO = new OrdertableDTO();
        ordertableDTO.setPk(orderPk);
        List<MenuOptionDTO> options = null;

        SqlSession session = sqlSessionFactory.openSession();
        try{
            ordertableDTO = session.selectOne("mapper.OrdertableMapper.inquiryOrderByPk", orderPk);
            options = session.selectList("mapper.MenuOptionMapper.inquiryOptionAll");
            session.update("mapper.OrdertableMapper.acceptOrder", ordertableDTO);
            ordertableDTO = session.selectOne("mapper.OrdertableMapper.menuOrderJoin", orderPk);
            session.commit();
        }catch(Exception e){
            session.rollback();
        }
        finally {
            session.close();
        }

        for(MenuOptionDTO option: options) {
            ordertableDTO.getOptions().add(option);
        }

        return ordertableDTO;
    }*/

    // 주문이력조회
/*    public List<OrdertableDTO> inquiryDeliveryFinish(int userPk){

        List<OrdertableDTO> list = null;
        List<MenuOptionDTO> options = null;
        OrdertableDTO ordertableDTO = new OrdertableDTO();
        ordertableDTO.setOrdertable_user_pk(userPk);

        SqlSession session = sqlSessionFactory.openSession();

        try{
            list = session.selectList("mapper.OrdertableMapper.inquiryDeliveryFinish", userPk);
            options = session.selectList("mapper.MenuOptionMapper.inquiryOptionAll");
        } finally {
            session.close();
        }

        return list;
    }*/

    // 주문취소
/*
    public OrdertableDTO cancelOrder(int orderPk){

        List<MenuOptionDTO> options = null;
        OrdertableDTO ordertableDTO = new OrdertableDTO();
        ordertableDTO.setPk(orderPk);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            ordertableDTO = session.selectOne("mapper.OrdertableMapper.inquiryOrderByPk", orderPk);
            options = session.selectList("mapper.MenuOptionMapper.inquiryOptionAll");
            session.update("mapper.OrdertableMapper.cancelOrder", ordertableDTO);
            ordertableDTO = session.selectOne("mapper.OrdertableMapper.inquiryOrderByPk", orderPk);
            ordertableDTO = session.selectOne("mapper.OrdertableMapper.menuOrderJoin", orderPk);

            if(ordertableDTO.getStatus().equals(Status.취소.name()))
                System.out.println("주문을 취소하였습니다.");
            else if(ordertableDTO.getStatus().equals(Status.배달중.name()))
                System.out.println("이미 배달중인 주문은 취소가 불가능합니다.");
            else
                System.out.println("이미 배달완료된 주문은 취소가 불가능합니다.");

            session.commit();
        } finally {
            session.close();
        }
        for(MenuOptionDTO option: options) {
            ordertableDTO.getOptions().add(option);
        }
        return ordertableDTO;
    }
*/

    public int insertOrder(MenuDTO menuDTO){
        Scanner sc = new Scanner(System.in);
        MenuDAO menuDAO = new MenuDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        SqlSession session = sqlSessionFactory.openSession();
        int rows = 0;
        try{
            if(menuDAO.isSaleable(menuDTO)) {
                menuDTO = menuDAO.materialExhaustion(menuDTO);

                System.out.println("가게 번호 입력해");
                int store_pk = sc.nextInt();
                System.out.println("사용자 번호 입력하시오.");
                int user_pk = sc.nextInt();
                System.out.println("요청사항을 입력하시오.");
                String request = sc.next();
                System.out.println("금액을 입력하시오.");
                int price = sc.nextInt();
                OrdertableDTO ordertableDTO = new OrdertableDTO(store_pk,user_pk, request, price, Status.접수대기.name());
                rows = session.insert("mapper.OrdertableMapper.insertOrder", ordertableDTO);
                System.out.println("주문 생성 되었습니다.");
                System.out.println("rows : " + rows);
            }
            else {
                System.out.println("재료소진으로 인해 주문 실패하였습니다.");
            }
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
        }
        finally
        {
            session.close();
        }
        return rows;
    }




}