package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.StoreDTO;

import java.util.List;


public class StoreDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public StoreDAO(SqlSessionFactory sqlSessionFactory)
    {
        this.sqlSessionFactory = sqlSessionFactory;
    }

/*    public int insertStore(StoreDTO storeDTO){
        SqlSession session = sqlSessionFactory.openSession();
        int rows = 0;
        try{
            rows = session.insert("mapper.StoreMapper.insertStore", storeDTO);
            session.commit();
        }catch (Exception e){
            session.rollback();
        }finally {
            session.close();
        }
        return rows;
    }*/

    public List<StoreDTO> insertStore(StoreDTO storeDTO){
        SqlSession session = sqlSessionFactory.openSession();
        List<StoreDTO> storeDTOS = null;

        try{
            storeDTOS = session.selectList("mapper.StoreMapper.insertStore", storeDTO);
            session.commit();
        }
        catch (Exception e)
        {
            session.rollback();
        } finally{
            session.close();
        }
        return storeDTOS;
    }


    public List<StoreDTO> inquiryStore(){
        SqlSession session = sqlSessionFactory.openSession();
        List<StoreDTO> list = null;
        System.out.println("[매장조회]");
        try {
            list = session.selectList("mapper.StoreMapper.inquiryStore");
        } finally {
            session.close();
        }
        return list;
    }



}