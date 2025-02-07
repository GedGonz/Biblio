package com.biblio.web.controller;

import com.biblio.web.model.AuthCreateUser;
import com.biblio.web.model.AuthLoginRequest;
import com.biblio.web.model.AuthResponse;
import com.biblio.web.security.UserDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserDetailService userDetailService;

    public AuthenticationController(UserDetailService userDetailService){
        this.userDetailService=userDetailService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Validated AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody @Validated AuthCreateUser authCreateUser){
        return new ResponseEntity<>(this.userDetailService.createUser(authCreateUser),HttpStatus.CREATED);
    }
}
