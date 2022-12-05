package persistence.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString

public class OrdertableDTO{
    private int pk;
    private String menuName;
    private int user_pk;

    private int store_pk;
    private String request;
    private Timestamp created_date;
    private String status;
    private int total;

    public OrdertableDTO(int store_pk, int user_pk, String request, int price, String name)
    {
        this.store_pk = store_pk;
        this.user_pk = user_pk;
        this.request = request;
        this.total = price;
        this.menuName= name;
    }

/*    @Override
    public String toString() {
        return
                "주문자 : " + nickName + ", 메뉴 : " + menuName + ", " +
                        "메뉴 옵션 : " +
                        (op1? options.get(0).getOpName() + " / ": "") +
                        (op2? options.get(1).getOpName() + " / ": "") +
                        (op3? options.get(2).getOpName() + " / ": "") +
                        (op4? options.get(3).getOpName() + ", ": "") +
                        "총액 : " + total + ", 상태 : " + status;
    }*/

}