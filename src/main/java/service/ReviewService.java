package service;

import persistence.MyBatisConnectionFactory;
import persistence.DAO.ReviewDAO;
import persistence.DTO.ReviewDTO;

import java.util.List;

public class ReviewService {
    private ReviewDAO reviewDAO;
    //private ReviewView reviewView;

    public ReviewService() {
        this.reviewDAO = new ReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        //this.reviewView = new ReviewView();
    }

    public void insertReview(int ordertable_pk, int user_pk, String contents, double rating) {
        ReviewDTO reviewDTO = new ReviewDTO(ordertable_pk, user_pk, contents, rating);

        int result = reviewDAO.insertReview(reviewDTO);
        //reviewView.insertResult(result);
    }

    public void selectReview() {
        List<ReviewDTO> reviews = reviewDAO.selectAll();
        //reviewView.printList(reviews);
    }

}
