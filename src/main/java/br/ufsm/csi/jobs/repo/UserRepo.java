package br.ufsm.csi.jobs.repo;

import br.ufsm.csi.jobs.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email);
}
