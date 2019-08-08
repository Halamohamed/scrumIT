package com.scrum.DBmanagement.repositories;

import com.scrum.DBmanagement.moduller.SubTask;
import com.scrum.DBmanagement.moduller.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubTaskRepository extends MongoRepository<SubTask,String> {
    Optional<SubTask> findByName(@Param("name") String name);

    Optional<SubTask> findByUsers(@Param("user") User user);
}
