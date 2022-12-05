package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.ReviewDTO;

import java.util.List;
import java.util.Scanner;

public class ReviewDAO {
    SqlSessionFactory sessionFactory;

    public ReviewDAO(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int insertReview(ReviewDTO reviewDTO) {
        SqlSession session = sessionFactory.openSession();
        int rows = 0;
        try {
            rows = session.insert("mapper.ReviewMapper.insertReview", reviewDTO);
            session.commit();
        }catch (Exception e) {
            session.rollback();
        }finally {
            session.close();
        }
        return rows;
    }

    public List<ReviewDTO> selectAll() {
        SqlSession session = sessionFactory.openSession();
        List<ReviewDTO> reviews = null;
        try {
            reviews = session.selectList("mapper.ReviewMapper.selectAll");
        }finally {
            session.close();
        }
        return reviews;
    }

    public void inquiryReview(int user_pk){
        Scanner sc = new Scanner(System.in);
        List<ReviewDTO> list = null;
        String answer = "Y";
        int index = 0;
        int page = 1;
        final int AMOUNT = 2;

        SqlSession session = sessionFactory.openSession();

        try {
            list = session.selectList("mapper.ReviewMapper.inquiryReview", user_pk);
        } finally {
            session.close();
        }

        do {
            System.out.println(list.get(index));
            if((index+1) % AMOUNT == 0) {
                System.out.println("---------------------------------------------------------- [ " + page + " ]");
                System.out.println("다음 페이지를 보시겠습니까?(Y(y)/N(n))");
                answer = sc.next();
                page++;
            }
            index++;
        } while((answer.equals("Y")  || answer.equals("y")) && index < list.size());

        if(answer.equals("Y")  || answer.equals("y"))
        System.out.println("---------------------------------------------------------- [ " + page + " ]");

    }

}
