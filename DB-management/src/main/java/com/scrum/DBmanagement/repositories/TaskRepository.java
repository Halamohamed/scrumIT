package com.scrum.DBmanagement.repositories;

import com.scrum.DBmanagement.moduller.SubTask;
import com.scrum.DBmanagement.moduller.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task,String> {

    Optional<Task> findByName(@Param("name") String name);

    Optional<Task> findByPriority(@Param("priority") Integer priority);

    Optional<Task> findBySubTasks(@Param("subTask") SubTask subTask);
}
