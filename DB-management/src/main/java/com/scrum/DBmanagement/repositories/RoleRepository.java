package com.scrum.DBmanagement.repositories;

import com.scrum.DBmanagement.moduller.Role;
import com.scrum.DBmanagement.moduller.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleRepository extends MongoRepository<Role,String> {
    Role findByName(RoleName name);
}
