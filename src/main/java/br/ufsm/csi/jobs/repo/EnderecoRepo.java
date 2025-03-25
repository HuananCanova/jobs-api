package br.ufsm.csi.jobs.repo;

import br.ufsm.csi.jobs.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepo extends JpaRepository<Endereco, Long> {
}
