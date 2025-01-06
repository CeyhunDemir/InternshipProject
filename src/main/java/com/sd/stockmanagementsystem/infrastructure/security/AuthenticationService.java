package com.sd.stockmanagementsystem.infrastructure.security;

import com.sd.stockmanagementsystem.application.dto.request.AuthenticationRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.RegisterRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.AuthenticationResponseDTO;
import com.sd.stockmanagementsystem.domain.enumeration.UserEnumeration;
import com.sd.stockmanagementsystem.domain.exception.AuthException;
import com.sd.stockmanagementsystem.domain.model.User;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO) throws AuthException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDTO.getEmail(),
                            authenticationRequestDTO.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new AuthException("Invalid mail or password!"); // Custom exception with message
        }
        var user = userRepository.findByEmail(authenticationRequestDTO.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(jwtToken);
    }
    @Transactional
    public AuthenticationResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        var user = User.builder()
                .firstName(registerRequestDTO.getFirstName())
                .lastName(registerRequestDTO.getLastName())
                .email(registerRequestDTO.getEmail())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .role(UserEnumeration.Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(jwtToken);
    }
}

