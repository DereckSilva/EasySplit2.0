package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.PersonRequestDTO;
import com.easy_split.demo.dtos.response.PersonResponseDTO;
import com.easy_split.demo.entities.Person;

public class PersonMapper {

    public static Person toEntity(PersonRequestDTO person) {
        return new Person(person.name(), person.birthDate());
    }

    public static PersonResponseDTO toDTO(Person person) {
        return new PersonResponseDTO(person.getId(), person.getName(), person.getBirthdate());
    }
}
