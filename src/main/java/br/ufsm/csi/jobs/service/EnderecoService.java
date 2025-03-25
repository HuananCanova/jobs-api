package br.ufsm.csi.jobs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufsm.csi.jobs.model.Endereco;
import br.ufsm.csi.jobs.repo.EnderecoRepo;

@Service
public class EnderecoService {
    private final EnderecoRepo enderecoRepo;

    @Autowired
    public EnderecoService(EnderecoRepo enderecoRepo) {
        this.enderecoRepo = enderecoRepo;
    }

    @Transactional
    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepo.save(endereco);
    }

}
