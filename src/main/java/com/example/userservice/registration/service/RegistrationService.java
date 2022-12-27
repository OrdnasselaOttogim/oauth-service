package com.example.userservice.registration.service;

import com.example.userservice.appuser.model.AppUser;
import com.example.userservice.appuser.model.UserRole;
import com.example.userservice.appuser.service.UserService;
import com.example.userservice.registration.EmailValidator;
import com.example.userservice.registration.RegistrationRequest;
import com.example.userservice.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    ConfirmationTokenService confirmationTokenService;


    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER,
                        request.getAddress()
                )
        );

    }



}
