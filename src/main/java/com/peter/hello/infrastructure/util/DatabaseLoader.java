package com.peter.hello.infrastructure.util;

import com.peter.hello.domain.user.User;
import com.peter.hello.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) {
        loadTestUsers();
    }

    private void loadTestUsers() {
        User user = User.builder()
                .email("test@gmail.com")
                .name("peter")
                .password(passwordEncoder.encode("1234"))
                .build();
        userRepository.save(user);
    }
}
