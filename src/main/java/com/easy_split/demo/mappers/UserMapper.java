package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.UserRequestDTO;
import com.easy_split.demo.dtos.response.UserResponseDTO;
import com.easy_split.demo.entities.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO userRequestDTO) {
        return new User(userRequestDTO.email(), userRequestDTO.password(), userRequestDTO.role());
    }

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(), user.getEmail(), user.getRole()
        );
    }
}
