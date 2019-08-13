package com.scrum.DBmanagement.resources;

import com.scrum.DBmanagement.moduller.Team;
import com.scrum.DBmanagement.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamRecources {

   /* @Autowired
    private RestTemplate restTemplate;
    private String teamId;

    @GetMapping("/teams")
    public Team getTeam(){
        Team team = restTemplate.getForObject("http://localhost:8082/teams" + teamRepository.findById(teamId), Team.class);
    return team;
    }*/
   private final Logger log = LoggerFactory.getLogger(TeamRecources.class);

    @Autowired
    private TeamRepository repository;

    public TeamRecources(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/teams")
    Collection<Team> teams() {
        return repository.findAll();
    }

    @GetMapping("/getteam/{id}")
    ResponseEntity<?> getTeam(@PathVariable String id) {
        Optional<Team> team = repository.findById(id);
        return team.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/newteam", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Team> createTeam(@Valid @RequestBody Team team) throws URISyntaxException {
        log.info("Request to create team: {}", team);
        Team result = repository.save(team);
        return ResponseEntity.created(new URI("/api/team/" + result.getId())).body(result);
    }

    @PutMapping("/team")
    ResponseEntity<Team> updateTeam(@Valid @RequestBody Team team) {
        log.info("Request to update team: {}", team);
        Team result = repository.save(team);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/deleteteam/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable String id) {
        log.info("Request to delete team: {}", id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
