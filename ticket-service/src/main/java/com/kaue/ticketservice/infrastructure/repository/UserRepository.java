package com.kaue.ticketservice.infrastructure.repository;

import com.kaue.ticketservice.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
