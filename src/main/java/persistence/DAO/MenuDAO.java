package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.MenuDTO;
import persistence.DTO.MenuOptionDTO;

import java.util.List;

public class MenuDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public MenuDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int selectStoreKeyByStoreName(String storeName)
    {
        SqlSession session = sqlSessionFactory.openSession();
        int key = 0;
        try
        {
            key = session.selectOne("mapper.StoreMapper.selectStoreKeyByStoreName", storeName);
        }
        catch (Exception e) {
            session.rollback();
        }
        finally {
            session.close();
        }
        return key;
    }

    public List<MenuDTO> insertMenu(MenuDTO menuDTO){
        SqlSession session = sqlSessionFactory.openSession();
        List<MenuDTO> menuDTOS = null;
        //int rows = 0;
        try{
            menuDTOS = session.selectList("mapper.MenuMapper.insertMenu", menuDTO);
            session.commit();
        }catch (Exception e){
            session.rollback();
        }finally {
            session.close();
        }
        return menuDTOS;
    }

    public List<MenuDTO> selectMenu(String menuName) {
        List<MenuDTO> menuDTOS = null;

        SqlSession session = sqlSessionFactory.openSession();
        try {
            menuDTOS = session.selectList("mapper.MenuMapper.selectMenu", menuName);
        } finally {
            session.close();
        }
        return menuDTOS;
    }

    public List<MenuDTO> selectAll() {
        List<MenuDTO> menuDTOS = null;
        List<MenuOptionDTO> options = null;

        SqlSession session = sqlSessionFactory.openSession();
        try {
            menuDTOS = session.selectList("mapper.MenuMapper.selectAll");
        } finally {
            session.close();
        }
        return menuDTOS;
    }

    public int changeMenuNameAndPrice(MenuDTO menuDTO) {
        List<MenuOptionDTO> options = null;
        SqlSession session = sqlSessionFactory.openSession();

        int rows = 0;
        try {
            rows =  session.update("mapper.MenuMapper.changeMenu", menuDTO);
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }

        return rows;
    }

/*    public boolean isSaleable(int pk) {
        List<MenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("mapper.MenuMapper.inquiryLimitedQuantity", pk);
        } finally {
            session.close();
        }

        if (list.get(0).getLimitedQuantity() == 0) {
            return false;
        }
        return true;
    }*/

    public boolean isSaleable(MenuDTO menuDTO) {
        List<MenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("mapper.MenuMapper.inquiryLimitedQuantity", menuDTO);

        }
        finally {
            session.close();
        }


        return true;
    }

/*    public MenuDTO materialExhaustion(int menu_pk) {

        MenuDTO menuDTO = new MenuDTO();
        SqlSession session = sqlSessionFactory.openSession();

        try {
            if (isSaleable(menu_pk)) {
                session.update("mapper.MenuMapper.materialExhaustion", menu_pk);
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return menuDTO;
    }*/

    public MenuDTO materialExhaustion(MenuDTO menuDTO) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            if (isSaleable(menuDTO)) {
                session.update("mapper.MenuMapper.materialExhaustion", menuDTO);
                session.commit();
            }
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return menuDTO;
    }

}
