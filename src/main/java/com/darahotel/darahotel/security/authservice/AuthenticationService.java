package com.darahotel.darahotel.security.authservice;

import com.darahotel.darahotel.security.auth.AutenticationResponse;
import com.darahotel.darahotel.security.auth.AuthenticationRequest;
import com.darahotel.darahotel.security.auth.RegistrationRequest;
import com.darahotel.darahotel.security.common.JwtService;
import com.darahotel.darahotel.security.common.Role;
import com.darahotel.darahotel.security.common.UserRepository;
import com.darahotel.darahotel.security.entitysecurity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AutenticationResponse register(RegistrationRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AutenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AutenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AutenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
