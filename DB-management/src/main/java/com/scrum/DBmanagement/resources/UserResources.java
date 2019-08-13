package com.scrum.DBmanagement.resources;

import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import com.scrum.DBmanagement.moduller.User;
import com.scrum.DBmanagement.moduller.UserList;
import com.scrum.DBmanagement.repositories.UserRepository;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserResources {
    @Autowired
    private RestTemplate restTemplate;
    //private String userId;

   /* @GetMapping("/users")
    public User getUsers(){
       User user = restTemplate.getForObject("http://localhost:8082/users/" +userRepository.findById(userId), User.class);
        return user;
    }*/
    private final Logger log = LoggerFactory.getLogger(UserResources.class);

    @Autowired
    private UserRepository repository;

    public UserResources(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    Collection<User> users() {
        return repository.findAll();
    }

    @GetMapping("/getuser/{id}")
    ResponseEntity<?> getUser(@PathVariable String id) {
        Optional<User> user = repository.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/userbyname/{userName}")
    ResponseEntity<?> getUserByUserName(@PathVariable String userName) {
        Optional<User> user = Optional.ofNullable(repository.findByUsername(userName));
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/newuser", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        log.info("Request to create user: {}", user);
        User result = repository.save(user);
        return ResponseEntity.created(new URI("/api/user/" + result.getUserId())).body(result);
    }

    @PutMapping("/user")
    ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        log.info("Request to update user: {}", user);
        User result = repository.save(user);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        log.info("Request to delete user: {}", id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
