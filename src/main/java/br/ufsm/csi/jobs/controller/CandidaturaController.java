package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Candidatura;
import br.ufsm.csi.jobs.service.CandidaturaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/candidatura")
public class CandidaturaController {

    private final CandidaturaService candidaturaService;

    public CandidaturaController(CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Candidatura> createCandidatura(@RequestBody @Valid Candidatura candidatura,
                                                         Authentication authentication,
                                                         UriComponentsBuilder uriBuilder) {
        Candidatura savedCandidatura = candidaturaService.createCandidatura(candidatura, authentication.getName());
        URI uri = uriBuilder.path("/candidatura/{id}").buildAndExpand(savedCandidatura.getId()).toUri();
        return ResponseEntity.created(uri).body(savedCandidatura);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidatura> getCandidaturaById(@PathVariable Long id) {
        Candidatura candidatura = candidaturaService.getCandidaturaById(id)
                .orElseThrow(() -> new NoSuchElementException("Candidatura com ID " + id + " não encontrada"));
        return ResponseEntity.ok(candidatura);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Candidatura>> getAllCandidaturas() {
        List<Candidatura> candidaturas = candidaturaService.getAllCandidaturas();
        return ResponseEntity.ok(candidaturas);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> deleteCandidatura(@PathVariable Long id, Authentication authentication) {
        Candidatura candidatura = candidaturaService.getCandidaturaById(id)
                .orElseThrow(() -> new NoSuchElementException("Candidatura com ID " + id + " não encontrada"));
        if (!candidatura.getUser().getEmail().equals(authentication.getName())) {
            throw new AccessDeniedException("Você não tem permissão para deletar esta candidatura");
        }
        String descricaoVaga = candidatura.getVaga() != null ? candidatura.getVaga().getDescricao() : "sem descrição";
        candidaturaService.deleteCandidatura(id);
        return ResponseEntity.ok("Candidatura com ID " + id + " associada à vaga '" + descricaoVaga + "' foi deletada com sucesso.");
    }
}