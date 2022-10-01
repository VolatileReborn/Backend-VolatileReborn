package com.example.BackendVolatile.dao.UserDao;

import com.example.BackendVolatile.dto.userDTO.RegisterDTO;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Setter(AccessLevel.NONE)
    private Long user_id;

    private String phone_number;
    private String nick_name;
    private Integer role;
    private String password;
    private String professional_skill;
    private String device;


    public User(RegisterDTO registerDTO){
        this.nick_name = registerDTO.getNickname();
        this.phone_number = registerDTO.getPhone_number();
        this.role = registerDTO.getRole();

    }

}
