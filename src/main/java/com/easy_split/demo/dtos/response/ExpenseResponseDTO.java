package com.easy_split.demo.dtos.response;

import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesRequestDTO;
import com.easy_split.demo.entities.Payments;
import com.easy_split.demo.entities.Person;

import java.time.LocalDate;
import java.util.List;

public record ExpenseResponseDTO(String name, Double price, Integer parcels, Boolean intermediary, LocalDate maturity, Boolean paid, Person payee, List<Payments> payments) {
}
