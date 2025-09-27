package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.CreateUserRequestDTO;
import com.easy_split.demo.dtos.requests.UpdatedUserDTO;
import com.easy_split.demo.dtos.response.UserResponseDTO;
import com.easy_split.demo.dtos.response.UserResponseUpdatedDTO;
import com.easy_split.demo.entities.User;

public class UserMapper {

    public static User toEntity(CreateUserRequestDTO userRequestDTO) {
        return new User(userRequestDTO.getEmail(), userRequestDTO.getPassword(), userRequestDTO.getRole());
    }

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(), user.getEmail(), user.getRole()
        );
    }

    public static User toEntityUp(UpdatedUserDTO updatedUserDTO) {
        return new User(updatedUserDTO.getId(), updatedUserDTO.getEmail(), updatedUserDTO.getPassword(), updatedUserDTO.getRole());
    }

    public static UserResponseUpdatedDTO toDTOUp(User user) {
        return new UserResponseUpdatedDTO(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
