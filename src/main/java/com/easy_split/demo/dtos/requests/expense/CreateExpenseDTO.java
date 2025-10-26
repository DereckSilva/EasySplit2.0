package com.easy_split.demo.dtos.requests.expense;

import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesRequestDTO;
import com.easy_split.demo.entities.Person;

import java.time.LocalDate;
import java.util.List;

public record CreateExpenseDTO(String name, Double price, Integer parcels, Boolean intermediary, LocalDate maturity, Boolean paid, Person payee, List<IntermediariesRequestDTO> intermediaries, String barCode) {
}
