package com.scrum.DBmanagement.moduller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class User {

    @Id
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String city;
    private RoleName role;
    private boolean active;

    private String username;

    private String password;
    private String activity;

    private  String passwordConfirm;
    public boolean changeActive(){
        return active=!active ;
    }
}
