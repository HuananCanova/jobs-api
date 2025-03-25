package br.ufsm.csi.jobs.repo;

import br.ufsm.csi.jobs.model.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepo extends JpaRepository<Candidatura, Long> {
}
