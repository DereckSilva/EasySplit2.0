package com.easy_split.demo.controllers;

import com.easy_split.demo.dtos.requests.AuthenticationDTO;
import com.easy_split.demo.dtos.response.AuthResponseDTO;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.services.AuthenticationService;
import com.easy_split.demo.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthenticationManager  authenticationManager;
    @Autowired
    public AuthenticationService authenticationService;
    @Autowired
    public TokenService  tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO user) {
        var userLogin = new UsernamePasswordAuthenticationToken(user.login(), user.password());
        var auth      = this.authenticationManager.authenticate(userLogin);

        String token  = this.tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
