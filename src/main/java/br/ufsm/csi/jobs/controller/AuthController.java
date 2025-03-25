package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.security.TokenServiceJWT;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenServiceJWT tokenServiceJWT;

    public AuthController(AuthenticationManager authenticationManager, TokenServiceJWT tokenServiceJWT) {
        this.authenticationManager = authenticationManager;
        this.tokenServiceJWT = tokenServiceJWT;
    }

    @PostMapping
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAuth dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        Authentication auth = authenticationManager.authenticate(authToken);
        String token = tokenServiceJWT.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }

    private record DadosTokenJWT(String token) {}
    private record DadosAuth(
            @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
            @NotBlank(message = "Senha é obrigatória") String senha) {}
}