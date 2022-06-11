package com.peter.hello.application.controller;

import com.peter.hello.application.payload.LoginResponse;
import com.peter.hello.domain.user.User;
import com.peter.hello.domain.user.service.PeterUserDetailService;
import com.peter.hello.infrastructure.security.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtHelper jwtHelper;
    private final PeterUserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public LoginResponse login(@RequestParam String email, @RequestParam String password) {
        User userDetails = userDetailService.loadUserByUsername(email);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            Map<String, String> claims = new HashMap<>();
            claims.put("email", email);
            String authorities = userDetails
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
            claims.put("authorities", authorities);
            claims.put("id", String.valueOf(userDetails.getId()));
            String jwt = jwtHelper.createJwtForClaims(email, claims);
            return new LoginResponse(jwt);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user not authenticated");
    }
}
