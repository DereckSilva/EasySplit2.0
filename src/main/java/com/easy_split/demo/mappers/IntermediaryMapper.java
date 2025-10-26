package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesDTO;
import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesRequestDTO;

public class IntermediaryMapper {

    public static IntermediariesRequestDTO toIntermediariesRequestDTO(IntermediariesDTO  intermediariesDTO) {
        return new IntermediariesRequestDTO(intermediariesDTO.person(), intermediariesDTO.price());
    }
}
