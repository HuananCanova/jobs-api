package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.model.Empresa;
import br.ufsm.csi.jobs.repo.EmpresaRepo;
import br.ufsm.csi.jobs.repo.VagaRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepo empresaRepo;
    private final EnderecoService enderecoService;
    private final VagaRepo vagaRepo;

    public EmpresaService(EmpresaRepo empresaRepo, EnderecoService enderecoService, VagaRepo vagaRepo) {
        this.empresaRepo = empresaRepo;
        this.enderecoService = enderecoService;
        this.vagaRepo = vagaRepo;
    }

    @Transactional
    public Empresa createEmpresa(Empresa empresa) {
        if (empresa.getEndereco() != null) {
            // Certifique-se de que o Endereco seja persistido antes da Empresa
            enderecoService.salvarEndereco(empresa.getEndereco());
        }
        empresaRepo.save(empresa);
        return empresa;
    }



    public Optional<Empresa> getEmpresaById(Long id) {
        return empresaRepo.findById(id);
    }

    public Optional<Empresa> findEmpresaByRazaoSocial(String razaoSocial) {
        return empresaRepo.findByRazaoSocial(razaoSocial);
    }

    public List<Empresa> findAllEmpresas() {
        return empresaRepo.findAll();
    }

    public void deleteEmpresaById(Long id){
        if (empresaRepo.existsById(id)) {
            empresaRepo.deleteById(id);
        }
    }

    @Transactional
    public void updateEmpresa(Empresa empresa) {
        empresaRepo.save(empresa);
    }


    @Transactional
    public void deleteVagasByEmpresaId(Long id) {
        vagaRepo.deleteByEmpresaId(id);
    }

}
