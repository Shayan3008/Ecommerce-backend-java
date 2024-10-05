package com.ecommerce_backend.ecommerce.auth.mappers;

import org.springframework.stereotype.Component;

import com.ecommerce_backend.ecommerce.auth.dto.request.UserRequestDTO;
import com.ecommerce_backend.ecommerce.auth.dto.response.UserResponseDTO;
import com.ecommerce_backend.ecommerce.auth.models.Users;

@Component
public class UserMapper {
    public Users requestToDomain(UserRequestDTO userRequestDTO){
        Users users = new Users();
        users.setEmail(userRequestDTO.getEmail());
        users.setName(userRequestDTO.getName());
        users.setHashedPassword(userRequestDTO.getHashedPassword());
        return users;
    }

    public UserResponseDTO domainToResponse(Users user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }
}
