package com.sd.stockmanagementsystem.infrastructure.adapter.in.controller;


import com.sd.stockmanagementsystem.application.dto.request.AuthenticationRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.RegisterRequestDTO;
import com.sd.stockmanagementsystem.application.dto.response.AuthenticationResponseDTO;
import com.sd.stockmanagementsystem.infrastructure.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @Valid @RequestBody AuthenticationRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
