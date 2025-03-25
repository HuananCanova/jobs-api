package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.model.Candidatura;
import br.ufsm.csi.jobs.model.Usuario;
import br.ufsm.csi.jobs.repo.CandidaturaRepo;
import br.ufsm.csi.jobs.repo.UserRepo;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CandidaturaService {
    private final CandidaturaRepo candidaturaRepo;
    private final UserRepo userRepo;

    public CandidaturaService(CandidaturaRepo candidaturaRepo, UserRepo userRepo) {
        this.candidaturaRepo = candidaturaRepo;
        this.userRepo = userRepo;
    }

    public Candidatura createCandidatura(Candidatura candidatura, String userEmail) {
        Usuario usuario = userRepo.findByEmail(userEmail);
        if (usuario == null) {
            throw new NoSuchElementException("Usuário com email " + userEmail + " não encontrado");
        }
        candidatura.setUser(usuario);
        return candidaturaRepo.save(candidatura);
    }

    public List<Candidatura> getAllCandidaturas() {
        return candidaturaRepo.findAll();
    }

    public Optional<Candidatura> getCandidaturaById(Long id) {
        return candidaturaRepo.findById(id)
                .map(candidatura -> {
                    Hibernate.initialize(candidatura.getUser());
                    Hibernate.initialize(candidatura.getVaga());
                    return candidatura;
                });
    }

    public void deleteCandidatura(Long id) {
        if (!candidaturaRepo.existsById(id)) {
            throw new NoSuchElementException("Candidatura com ID " + id + " não encontrada");
        }
        candidaturaRepo.deleteById(id);
    }
}