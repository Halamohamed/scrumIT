package com.scrum.DBmanagement.moduller;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Document(collection = "user")
public class User {

    @Id
    private String userId;
    private String name;
    @Email
    private String email;
    private String phone;
    private String city;
    private RoleName role;
    private boolean active;
    private List<Role> roles;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String activity;
    @NotBlank
    private  String passwordConfirm;
    public boolean changeActive(){
        return active=!active ;
    }
}
