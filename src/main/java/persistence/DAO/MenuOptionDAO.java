package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.MenuOptionDTO;

import java.util.List;

public class MenuOptionDAO
{
     private final SqlSessionFactory sqlSessionFactory;

    public MenuOptionDAO(SqlSessionFactory sqlSessionFactory)
    {
            this.sqlSessionFactory = sqlSessionFactory;
    }
    public int insertOption(MenuOptionDTO menuOptionDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        int rows = 0;

        try {
            rows = session.insert("mapper.MenuOptionMapper.insertOption", menuOptionDTO);
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return rows;
    }

/*    public List<MenuOptionDTO> insertOption(MenuOptionDTO menuOptionDTO){
        SqlSession session = sqlSessionFactory.openSession();
        List<MenuOptionDTO> menuOptionDTOS = null;

        try{
            menuOptionDTOS = session.selectList("mapper.MenuOptionMapper.insertOption", menuOptionDTO);
            session.commit();
        }
        catch (Exception e)
        {
            session.rollback();
        } finally{
            session.close();
        }
        return menuOptionDTOS;
    }*/

    public List<MenuOptionDTO> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();
        List<MenuOptionDTO> menuOptionDTOS = null;

        try {
            menuOptionDTOS = session.selectList("mapper.MenuOptionMapper.selectAll");
        }
        finally {
            session.close();
        }
        return menuOptionDTOS;
    }



}
