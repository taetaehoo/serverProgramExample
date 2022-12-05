package persistence.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor

public class StoreDTO {

    private int pk;
    private String storeName;
    private int user_pk;
    private int orderCnt;
    private String introduction;
    private String address;
    private String pNumber;
    private Time openTime;
    private Time closeTime;
    private String name;

    @Override
    public String toString() {
        return
                "가게명 : " + storeName +
                        ", 소개 : " + introduction +
                        ", 주소 : " + address +
                        ", 가게전화번호 : " + pNumber +
                        ", 점주명 : " + name;
    }

    public StoreDTO(String storeName, int user_pk, String introduction, String address, String pNumber)
    {
        this.storeName = storeName;
        this.user_pk = user_pk;
        this.introduction = introduction;
        this.address = address;
        this.pNumber = pNumber;
    }


}