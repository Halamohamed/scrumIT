package com.scrum.DBmanagement.resources;

import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import com.scrum.DBmanagement.moduller.User;
import com.scrum.DBmanagement.moduller.UserList;
import com.scrum.DBmanagement.repositories.UserRepository;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResources {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    private String userId;

    @GetMapping("/users")
    public User getUsers(){
       User user = restTemplate.getForObject("http://localhost:8082/users/" +userRepository.findById(userId), User.class);
        return user;
    }

}
