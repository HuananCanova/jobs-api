package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Usuario;
import br.ufsm.csi.jobs.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/empresa")
    public ResponseEntity<Usuario> createUserEmpresa(@RequestBody @Valid Usuario user, UriComponentsBuilder uriBuilder) {
        user.getRoles().add("ROLE_EMPRESA");
        Usuario savedUser = userService.createUser(user);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @PostMapping("/candidato")
    public ResponseEntity<Usuario> createUserCandidato(@RequestBody @Valid Usuario user, UriComponentsBuilder uriBuilder) {
        user.getRoles().add("ROLE_USER");
        Usuario savedUser = userService.createUser(user);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }
}