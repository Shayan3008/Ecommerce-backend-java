package com.ecommerce_backend.ecommerce.auth.dto.response;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private String userName;
    private String email;
    private String token;
}
