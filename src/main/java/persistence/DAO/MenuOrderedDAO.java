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

                System.out.println("?????? ?????? ?????????");
                int store_pk = sc.nextInt();
                System.out.println("????????? ?????? ???????????????.");
                int user_pk = sc.nextInt();
                System.out.println("??????????????? ???????????????.");
                String request = sc.next();
                System.out.println("????????? ???????????????.");
                int price = sc.nextInt();
                OrdertableDTO ordertableDTO = new OrdertableDTO(store_pk, user_pk, request, price, Status.????????????.name());
                rows = session.insert("mapper.OrdertableMapper.insertOrder", ordertableDTO);
                System.out.println("?????? ?????? ???????????????.");
                System.out.println(rows);
            }
            else {
                System.out.println("?????????????????? ?????? ?????? ?????????????????????.");
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
