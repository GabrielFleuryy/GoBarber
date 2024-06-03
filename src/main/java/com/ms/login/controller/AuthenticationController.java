package com.ms.login.controller;

import com.ms.login.infra.security.JWTConfig;
import com.ms.login.model.User;
import com.ms.login.record.LoginDTO;
import com.ms.login.record.TokenDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTConfig jwtConfig;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO){
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password());

        var authentication = manager.authenticate(authenticationToken);

        var token = jwtConfig.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }
}
