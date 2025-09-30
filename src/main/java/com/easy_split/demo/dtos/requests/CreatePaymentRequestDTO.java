package com.easy_split.demo.dtos.requests;

import lombok.Data;

@Data
public class CreatePaymentRequestDTO {

    // create annotation and verify if field intermediary is true
    private IntermediaryRequestDTO intermediaryRequestDTO;
}
