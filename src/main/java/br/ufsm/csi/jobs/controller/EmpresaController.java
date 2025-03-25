package br.ufsm.csi.jobs.controller;

import br.ufsm.csi.jobs.model.Empresa;
import br.ufsm.csi.jobs.service.EmpresaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody @Valid Empresa empresa, UriComponentsBuilder uriBuilder) {
        Empresa savedEmpresa = empresaService.createEmpresa(empresa);
        URI uri = uriBuilder.path("/empresa/{id}").buildAndExpand(savedEmpresa.getId()).toUri();
        return ResponseEntity.created(uri).body(savedEmpresa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id) {
        Empresa empresa = empresaService.getEmpresaById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + id + " não encontrada"));
        return ResponseEntity.ok(empresa);
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresas = empresaService.findAllEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/getByRazaoSocial")
    public ResponseEntity<Empresa> getEmpresaByRazaoSocial(@RequestParam("razaoSocial") String razaoSocial) {
        Empresa empresa = empresaService.findEmpresaByRazaoSocial(razaoSocial)
                .orElseThrow(() -> new NoSuchElementException("Empresa com razão social " + razaoSocial + " não encontrada"));
        return ResponseEntity.ok(empresa);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody @Valid Empresa novaEmpresa, Authentication authentication) {
        Empresa empresa = empresaService.getEmpresaById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + id + " não encontrada"));
        // Verifica se o usuário autenticado é o dono da empresa (exemplo com email)
        if (!empresa.getEmail().equals(authentication.getName())) {
            throw new AccessDeniedException("Você não tem permissão para editar esta empresa");
        }
        empresa.setRazaoSocial(novaEmpresa.getRazaoSocial());
        empresa.setCNPJ(novaEmpresa.getCNPJ());
        empresa.setEmail(novaEmpresa.getEmail());
        empresaService.updateEmpresa(empresa);
        return ResponseEntity.ok(empresa);
    }

    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EMPRESA')")
    public ResponseEntity<String> deleteEmpresa(@PathVariable Long id, Authentication authentication) {
        Empresa empresa = empresaService.getEmpresaById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa com ID " + id + " não encontrada"));
        if (!empresa.getEmail().equals(authentication.getName())) {
            throw new AccessDeniedException("Você não tem permissão para deletar esta empresa");
        }
        String razaoSocial = empresa.getRazaoSocial();
        empresaService.deleteVagasByEmpresaId(id);
        empresaService.deleteEmpresaById(id);
        return ResponseEntity.ok("Empresa com ID " + id + " e razão social '" + razaoSocial + "' foi deletada com sucesso.");
    }
}