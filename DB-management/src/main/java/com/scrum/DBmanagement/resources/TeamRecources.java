package com.scrum.DBmanagement.resources;

import com.scrum.DBmanagement.moduller.Team;
import com.scrum.DBmanagement.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("/team")
public class TeamRecources {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RestTemplate restTemplate;
    private String teamId;

    @GetMapping("/teams")
    public Team getTeam(){
        Team team = restTemplate.getForObject("http://localhost:8082/teams" + teamRepository.findById(teamId), Team.class);
    return team;
    }
}
