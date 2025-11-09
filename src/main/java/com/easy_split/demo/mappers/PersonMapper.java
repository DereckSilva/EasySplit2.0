package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.person_user.PersonRequestDTO;
import com.easy_split.demo.dtos.response.PersonResponseDTO;
import com.easy_split.demo.entities.Person;

import java.time.format.DateTimeFormatter;

public class PersonMapper {

    private PersonMapper() {
        throw new IllegalStateException("Classe utilitária, não pode ser instanciada.");
    }

    public static Person toEntity(PersonRequestDTO person) {
        return new Person(person.getName(), person.getBirthdate());
    }

    public static PersonResponseDTO toDTO(Person person) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new PersonResponseDTO(person.getId(), person.getName(), person.getBirthdate().format(formatter));
    }
}
