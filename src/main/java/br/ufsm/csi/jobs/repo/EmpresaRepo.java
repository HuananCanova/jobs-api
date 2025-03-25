package br.ufsm.csi.jobs.repo;

import br.ufsm.csi.jobs.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepo extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findById(Long id);
    Optional<Empresa> findByRazaoSocial(String razaoSocial);
}
