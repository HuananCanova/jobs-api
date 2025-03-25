package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.model.Vaga;
import br.ufsm.csi.jobs.repo.VagaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {
    private final VagaRepo vagaRepo;

    @Autowired
    public VagaService(VagaRepo vagaRepo) {
        this.vagaRepo = vagaRepo;
    }

    public List<Vaga> listarVagas() {
        return vagaRepo.findAll();
    }


    public void saveVaga(Vaga vaga) {
        vagaRepo.save(vaga);
    }

    public void deleteById(Long id) {
        vagaRepo.existsById(id);
        vagaRepo.deleteById(id);
    }
    @Transactional
    public void updateVaga(Vaga vaga) {
        vagaRepo.save(vaga);
    }


    public Optional<Vaga> getVagaById(Long id) {
        return vagaRepo.findById(id);
    }
}
