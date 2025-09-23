package com.easy_split.demo.services;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User createUser(User user, Person person) {
        String passwordEncrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncrypted);
        user.setPerson(person);
        return this.userRepository.save(user);
    }

    public UserDetails getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
