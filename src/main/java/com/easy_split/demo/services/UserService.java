package com.easy_split.demo.services;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.repositories.PaymentRepository;
import com.easy_split.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PersonService personService;

    @Autowired
    public UserService(UserRepository userRepository, PersonService personService) {
        this.userRepository = userRepository;
        this.personService = personService;
    }


    public Optional<User> getUserById(int id) {
        return this.userRepository.findById(id);
    }

    public Optional<User> getUserEmail(String email) { return this.userRepository.findUserByEmail(email); }

    public User createUser(User user, Person person) {
        String passwordEncrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncrypted);
        user.setPerson(person);
        return this.userRepository.save(user);
    }

    public UserDetails getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User userUpdated(User user) {
        if (user.getPassword() != null) user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (user.getPerson() != null) user.setPerson(user.getPerson());
        return this.userRepository.save(user);
    }

    @Transactional
    public Optional userRemoved(User user) {
        Person person = user.getPerson();
        this.personService.removePerson(person);
        this.userRepository.delete(user);
        return Optional.empty();
    }

}
