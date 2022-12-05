package persistence.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class UserDTO implements Serializable {
    private int pk;
    private String role;
    private String name;
    private String id;
    private String pw;
    private String nickname;
    private String pNumber;
    private String address;
    private int age;

    public UserDTO(String name, String id, String pw, String nickname, String pNumber, String address, int age )
    {
        this.name = name;
        this.id = id;
        this. pw =  pw;
        this.nickname = nickname;
        this.pNumber = pNumber;
        this.address = address;
        this.age = age;
    }


}