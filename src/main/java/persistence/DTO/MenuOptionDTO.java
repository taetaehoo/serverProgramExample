package persistence.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class MenuOptionDTO {
    private int pk;
    private String opName;
    private int price;
    private int menu_pk;

    public MenuOptionDTO(String opName, int price, int menu_pk)
    {
        this.opName = opName;
        this.price = price;
        this.menu_pk = menu_pk;
    }
}

