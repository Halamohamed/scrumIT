package com.scrum.DBmanagement.repositories;

import com.scrum.DBmanagement.moduller.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
}
