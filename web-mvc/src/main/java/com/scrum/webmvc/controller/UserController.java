package com.scrum.webmvc.controller;

import com.netflix.discovery.DiscoveryClient;
import com.scrum.webmvc.moduller.User;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;


    /*public UserController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }*/
    @RequestMapping("")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> users(){
        return restTemplate.exchange("http://DBManagement/user", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
    }
    @GetMapping("{userId}")
    public User getUser(@PathVariable("userId") String userId){
        return new User();
    }


}
