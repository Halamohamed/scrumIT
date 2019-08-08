package com.scrum.webmvc.controller;

import com.scrum.webmvc.moduller.Team;
import com.scrum.webmvc.moduller.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/")
public class TeamController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getTeams(){
       return restTemplate.exchange("http://DBManagement/teams", HttpMethod.GET, null, new ParameterizedTypeReference<List<Team>>(){
        } );
    }
}
