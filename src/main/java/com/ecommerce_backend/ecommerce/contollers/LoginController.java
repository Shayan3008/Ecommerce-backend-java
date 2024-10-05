package com.ecommerce_backend.ecommerce.contollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce_backend.ecommerce.auth.dto.request.LoginReqDto;
import com.ecommerce_backend.ecommerce.auth.dto.request.UserRequestDTO;
import com.ecommerce_backend.ecommerce.auth.dto.response.LoginResponseDTO;
import com.ecommerce_backend.ecommerce.auth.models.Users;
import com.ecommerce_backend.ecommerce.common.config.CommonResponse;
import com.ecommerce_backend.ecommerce.common.exceptions.AlreadyPresentException;
import com.ecommerce_backend.ecommerce.common.exceptions.InvalidaInputException;
import com.ecommerce_backend.ecommerce.service.UserService;

@RestController
@RequestMapping("/auth")
public class LoginController {


    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponseDTO>> loginUser(@RequestBody LoginReqDto loginReqDto) {

        Users users = new Users();
        users.setEmail(loginReqDto.getEmail());
        users.setHashedPassword(loginReqDto.getPassword());
        users.setName(loginReqDto.getUserName());
        LoginResponseDTO loginResponseDTO = userService.generateTokenForUsers(users,"");
        return ResponseEntity.status(200)
                .body(new CommonResponse<>("User saved", HttpStatus.OK.value(), loginResponseDTO));

    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponse<LoginResponseDTO>> saveUser(@RequestBody UserRequestDTO userRequestDTO)
            throws InvalidaInputException, AlreadyPresentException {
        LoginResponseDTO userResponseDTO = userService.saveUser(userRequestDTO);
        CommonResponse<LoginResponseDTO> commonResponse = new CommonResponse<>("User Registered Successfully",
                HttpStatus.OK.value(), userResponseDTO);
        return ResponseEntity.status(200).body(commonResponse);

    }

    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }
}