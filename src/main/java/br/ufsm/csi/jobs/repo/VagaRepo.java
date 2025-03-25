package br.ufsm.csi.jobs.repo;

import br.ufsm.csi.jobs.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepo extends JpaRepository<Vaga, Long> {

    void deleteByEmpresaId(Long id);
}
