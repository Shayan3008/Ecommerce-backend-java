package com.ecommerce_backend.ecommerce.auth.dto.request;

import lombok.Data;

@Data
public class LoginReqDto {
    private String userName;
    private String password;
    private String email;
}
