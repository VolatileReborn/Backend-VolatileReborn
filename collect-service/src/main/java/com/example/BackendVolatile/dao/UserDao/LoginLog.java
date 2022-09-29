package com.example.BackendVolatile.dao.UserDao;

import lombok.*;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginLog implements Serializable {

    @Setter(AccessLevel.NONE)
    private Long login_id;

    private Long user_id;

    private Integer role;

    private Long login_time;

    private String login_event;
}
