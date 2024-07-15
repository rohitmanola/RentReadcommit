package com.crio.RentRead.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.crio.RentRead.Exception.*;
import com.crio.RentRead.Controller.exchanges.*;
import com.crio.RentRead.model.*;
import com.crio.RentRead.model.enums.Role;
import com.crio.RentRead.Repository.*;

@Service
public class AuthService {


    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    UserRepo userRepository;


    @Autowired
    AuthenticationManager authenticationManager;


    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("User already exists");
        }
        if (request.getRole() == null) {
            request.setRole(Role.USER);
        }
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        return userRepository.save(user);

    }

    
    public String login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        return "Login Successfull";
    }

}