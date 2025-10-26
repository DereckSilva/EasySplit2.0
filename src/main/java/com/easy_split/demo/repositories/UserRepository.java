package com.easy_split.demo.repositories;

import com.easy_split.demo.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    UserDetails findByEmail(String login);
    Optional<User> findUserByEmail(String email);
}
