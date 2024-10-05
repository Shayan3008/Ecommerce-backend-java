package com.ecommerce_backend.ecommerce.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce_backend.ecommerce.auth.JwtAuthConfig;
import com.ecommerce_backend.ecommerce.auth.dto.request.UserRequestDTO;
import com.ecommerce_backend.ecommerce.auth.dto.response.LoginResponseDTO;
import com.ecommerce_backend.ecommerce.auth.dto.response.UserResponseDTO;
import com.ecommerce_backend.ecommerce.auth.models.Users;
import com.ecommerce_backend.ecommerce.common.beans.BaseService;
import com.ecommerce_backend.ecommerce.common.beans.DaoFactory;
import com.ecommerce_backend.ecommerce.common.beans.MapperFactory;
import com.ecommerce_backend.ecommerce.common.exceptions.AlreadyPresentException;
import com.ecommerce_backend.ecommerce.common.exceptions.InvalidaInputException;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService extends BaseService{
        private PasswordEncoder passwordEncoder;
    
    public UserService(DaoFactory daoFactory,  MapperFactory mapperFactory,
            AuthenticationManager authenticationManager, JwtAuthConfig jwtAuthConfig,PasswordEncoder passwordEncoder,
            EntityManager entityManager) {
        super(daoFactory, mapperFactory, authenticationManager, jwtAuthConfig,entityManager);
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> getAllUsers() {

        List<Users> users = (List<Users>) getDaoFactory().getUsersDAO().findAll();
        return users.stream().map(e -> getMapperFactory().getUserMapper().domainToResponse(e)).toList();
    }

    public LoginResponseDTO saveUser(UserRequestDTO userRequestDTO)
            throws InvalidaInputException, AlreadyPresentException {
        String hashPassword = passwordEncoder.encode(userRequestDTO.getHashedPassword());
        Users users = getMapperFactory().getUserMapper().requestToDomain(userRequestDTO);
        users.setHashedPassword(hashPassword);
        // String validation = getValidatorFactory().getUserValidator().validateUser(users);
        // if (validation != null) {
        //     throw new InvalidInputException(validation);
        // }
        if (getDaoFactory().getUsersDAO().findByEmail(userRequestDTO.getEmail()) != null) {
            throw new AlreadyPresentException("Email is already Present");
        }
        users = getDaoFactory().getUsersDAO().save(users);
        users.setHashedPassword(userRequestDTO.getHashedPassword());
        return generateTokenForUsers(users,hashPassword);

    }

    public LoginResponseDTO generateTokenForUsers(Users users,String hashedPassword) {

        if(!users.getHashedPassword().startsWith("$2a"))
            users.setHashedPassword(hashedPassword);

        String token = getJwtAuthConfig().createToken(users);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setEmail(users.getEmail());
        loginResponseDTO.setUserName(users.getName());
        loginResponseDTO.setToken(token);
        return loginResponseDTO;
    }
}
