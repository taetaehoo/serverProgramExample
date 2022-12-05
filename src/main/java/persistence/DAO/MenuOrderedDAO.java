package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.*;
import persistence.MyBatisConnectionFactory;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MenuOrderedDAO {

    Scanner sc = new Scanner(System.in);

    private final SqlSessionFactory sqlSessionFactory;

    public MenuOrderedDAO(SqlSessionFactory sqlSessionFactory)
    {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    public List<MenuOrderedDTO> selectAll() {
        List<MenuOrderedDTO> menuOrderedDTOS = null;

        SqlSession session = sqlSessionFactory.openSession();
        try {
            menuOrderedDTOS = session.selectList("mapper.MenuOrderedMapper.selectAll");
        } finally {
            session.close();
        }
        return menuOrderedDTOS;
    }

    /*public MenuOrderedDTO insertOrder(int ordertable_pk, int menu_pk, int menu_option_pk)
    {
        SqlSession session = sqlSessionFactory.openSession();
        MenuOrderedDTO menuOrderedDTO = new MenuOrderedDTO();

        menuOrderedDTO.setOrdertable_pk(ordertable_pk);
        menuOrderedDTO.setMenu_pk(menu_pk);
        menuOrderedDTO.setMenu_option_pk(menu_option_pk);

        return menuOrderedDTO;
    }*/

    public int insertMenuOrdered(MenuOrderedDTO menuOrderedDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        int rows = 0;

        try {
            rows = session.insert("mapper.MenuOrderedMapper.insertMenuOrdered", menuOrderedDTO);
            //dtos = session.selectList("mapper.MenuOptionMapper.inquiryOptionAll");
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return rows;
    }

    public int insertOrder(MenuDTO menuDTO){
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
                OrdertableDTO ordertableDTO = new OrdertableDTO(store_pk, user_pk, request, price, Status.접수대기.name());
                rows = session.insert("mapper.OrdertableMapper.insertOrder", ordertableDTO);
                System.out.println("주문 생성 되었습니다.");
                System.out.println(rows);
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
