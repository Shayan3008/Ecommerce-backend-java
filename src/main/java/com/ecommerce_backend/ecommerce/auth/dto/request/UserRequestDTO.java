package com.ecommerce_backend.ecommerce.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private String name;
    private String email;
    private String hashedPassword;
}
