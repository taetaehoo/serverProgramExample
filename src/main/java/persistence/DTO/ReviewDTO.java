package persistence.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor

public class ReviewDTO {
    private int pk;
    private int ordertable_pk;
    private String contents;
    private Timestamp created_date;
    private double rating;
    private int user_pk;
    private String reply;

/*    public void insertContents()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("주문 번호를 입력하시오. : ");
        review_ordertable_pk = sc.nextInt();
        System.out.print("내용을 입력하시오. : ");
        contents = sc.next();
        System.out.print("별점을 입력하시오. : ");
        rating = sc.nextInt();
    }*/

    public ReviewDTO(int ordertable_pk, int user_pk, String contents, double rating)
    {
        this.ordertable_pk = ordertable_pk;
        this.user_pk = user_pk;
        this.contents = contents;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                ", contents='" + contents + '\'' +
                ", created_date=" + created_date +
                ", rating=" + rating +
                '}';
    }
}