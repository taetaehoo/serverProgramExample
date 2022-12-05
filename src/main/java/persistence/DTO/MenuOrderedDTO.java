package persistence.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class MenuOrderedDTO {
    private int pk;
    private int ordertable_pk;
    private int menu_pk;
    private int menu_option_pk;

}