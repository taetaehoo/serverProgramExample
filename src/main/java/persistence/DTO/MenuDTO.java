package persistence.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class MenuDTO
{
    private int pk;
    private int store_pk;
    private String menuName;
    private Integer price;
    private int limitedQuantity;
    private String series;

    public MenuDTO() {}

    public MenuDTO(int store_pk, String menuName, Integer price, int limitedQuantity, String series) {
        this.store_pk = store_pk;
        this.menuName = menuName;
        this.price = price;
        this.limitedQuantity = limitedQuantity;
        this.series = series;
    }

    public MenuDTO(int pk, String menuName, Integer price) {
        this.pk = pk;
        this.menuName = menuName;
        this.price = price;
    }

    public MenuDTO(int pk, String menuName) {
        this.pk = pk;
        this.menuName = menuName;
    }

    public MenuDTO(int pk, Integer price) {
        this.pk = pk;
        this.price = price;
    }


    /*@Override
    public String toString() {
        return
                "분류 : " + series +
                        ", 이름 : " + menuName +
                        ", 기본 가격 : " + price +
                        "원, 가능한 옵션 : " +
                        (op1? options.get(0).getOpName() + "(+" +options.get(0).getPrice()+"원)": "") +
                        (op2? "/" + options.get(1).getOpName() + "(+" +options.get(1).getPrice()+"원)": "") +
                        (op3? "/" + options.get(2).getOpName() + "(+" +options.get(2).getPrice()+"원)": "") +
                        (op4? "/" + options.get(3).getOpName() + "(+" +options.get(3).getPrice()+"원)": "") +
                        ", 한정 수량 : " + limitedQuantity;
    }*/

}