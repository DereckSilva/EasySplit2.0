package com.easy_split.demo.controllers.auth;

import com.easy_split.demo.dtos.requests.AuthResquestDTO;
import com.easy_split.demo.dtos.response.AuthResponseDTO;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.services.AuthenticationService;
import com.easy_split.demo.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthenticationManager  authenticationManager;
    @Autowired
    public AuthenticationService authenticationService;
    @Autowired
    public TokenService  tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthResquestDTO user) {
        var userLogin = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        var auth      = this.authenticationManager.authenticate(userLogin);

        String token  = this.tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
