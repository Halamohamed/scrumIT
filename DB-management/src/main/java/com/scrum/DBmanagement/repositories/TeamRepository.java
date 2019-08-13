package com.scrum.DBmanagement.repositories;

import com.scrum.DBmanagement.moduller.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team, String> {

    Optional<Team> findByName(@Param("name") String name);
}
