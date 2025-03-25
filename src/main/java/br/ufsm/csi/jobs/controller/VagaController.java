package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Vaga;
import br.ufsm.csi.jobs.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaga")
public class VagaController {
    private final VagaService vagaService;

    @Autowired
    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<Vaga> createVaga(@RequestBody @Valid Vaga vaga, UriComponentsBuilder uriBuilder) {
        vagaService.saveVaga(vaga);
        URI uri = uriBuilder.path("/vaga/{id}").buildAndExpand(vaga.getId()).toUri();
        return ResponseEntity.created(uri).body(vaga);
    }


    @GetMapping
    public ResponseEntity<List<Vaga>> getAllVagas() {
        List<Vaga> vagas = vagaService.listarVagas();
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> getVagaById(@PathVariable Long id) {
        Optional<Vaga> vaga = vagaService.getVagaById(id);
        return ResponseEntity.ok(vaga.orElseThrow());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<Vaga> updateVaga(@PathVariable Long id, @RequestBody @Valid Vaga novaVaga) {
        Optional<Vaga> vagaExistente = vagaService.getVagaById(id);

        if (vagaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Vaga vaga = vagaExistente.get();

        vaga.setTitulo(novaVaga.getTitulo());
        vaga.setDescricao(novaVaga.getDescricao());
        vaga.setRequisitos(novaVaga.getRequisitos());

        vagaService.updateVaga(vaga);

        return ResponseEntity.ok(vaga);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<String> deleteVaga(@PathVariable Long id) {
        Optional<Vaga> vaga = vagaService.getVagaById(id);

        if (vaga.isPresent()) {
            String titulo = vaga.get().getTitulo();
            String descricao = vaga.get().getDescricao();

            vagaService.deleteById(id);

            return ResponseEntity.ok("Vaga com ID " + id + " e título '" + titulo + "' e descrição '" + descricao + "' foi deletada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
