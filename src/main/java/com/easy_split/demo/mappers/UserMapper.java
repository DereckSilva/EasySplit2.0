package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.UserRequestDTO;
import com.easy_split.demo.dtos.requests.UserRequestUpdatedDTO;
import com.easy_split.demo.dtos.response.UserResponseDTO;
import com.easy_split.demo.dtos.response.UserResponseUpdatedDTO;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.enums.Role;

public class UserMapper {

    public static User toEntity(UserRequestDTO userRequestDTO) {
        return new User(userRequestDTO.email(), userRequestDTO.password(), userRequestDTO.role());
    }

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(), user.getEmail(), user.getRole()
        );
    }

    public static User toEntityUp(UserRequestUpdatedDTO userRequestUpdatedDTO) {

        return new User(userRequestUpdatedDTO.id(),
                userRequestUpdatedDTO.email().toString(),
                userRequestUpdatedDTO.password().toString(),
                Role.valueOf(userRequestUpdatedDTO.role().toString())
        );
    }

    public static UserResponseUpdatedDTO toDTOUp(User user) {
        return new UserResponseUpdatedDTO(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
