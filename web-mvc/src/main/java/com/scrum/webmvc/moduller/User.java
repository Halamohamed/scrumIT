package com.scrum.webmvc.moduller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


    private String userId;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String role;
    private boolean active;

    private String username;

    private String password;
    private String activity;

    private  String passwordConfirm;
    public boolean changeActive(){
        return active=!active ;
    }
}
