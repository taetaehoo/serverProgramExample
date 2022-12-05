package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.*;
import java.util.List;

public class UserDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public UserDAO(SqlSessionFactory sqlSessionFactory)
    {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insertUser(UserDTO userDTO){
        SqlSession session = sqlSessionFactory.openSession();
        int rows = 0;
        try{
            rows = session.insert("mapper.UserMapper.insertUser", userDTO);
            session.commit();
        }
        catch (Exception e) {
            session.rollback();
        }
        finally {
            session.close();
        }
        return rows;
    }

    public List<UserDTO> selectAll(){
        List<UserDTO> userList = null;

        SqlSession session = sqlSessionFactory.openSession();

        try {
            userList = session.selectList("mapper.UserMapper.selectAll");
        }
        finally {
            session.close();
        }
        return userList;
    }

    public List<UserDTO> login(UserDTO userDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        List<UserDTO> users = null;
        try {
            users = session.selectList("mapper.UserMapper.login", userDTO);

        }finally {
            session.close();
        }
        return users;
    }

    public String getRole(int pk)
    {
        SqlSession session = sqlSessionFactory.openSession();
        String role = null;
        try
        {
            role = session.selectOne("mapper.UserMapper.selectRole", pk);
        }
        finally {
            session.close();
        }
        return role;
    }

}