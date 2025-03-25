package br.ufsm.csi.jobs.service;

import br.ufsm.csi.jobs.dto.UserDTO;
import br.ufsm.csi.jobs.model.Usuario;
import br.ufsm.csi.jobs.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public Usuario createUser(Usuario user) {
        user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));

        if (user.getRoles().equals("ROLE_USER")) {
            user.getRoles().add("ROLE_USER");
        } else if (user.getRoles().equals("ROLE_EMPRESA")) {
            user.getRoles().add("ROLE_EMPRESA");
        }
        userRepo.save(user);
        return user;
    }

    public List<Usuario> listUsers() {
        return userRepo.findAll();
    }

    public List<UserDTO> findALlUsers(){
        return this.userRepo.findAll().stream().map(UserDTO::new).toList();
    }

    public Optional<Usuario> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public UserDTO findUser(Long id){
        Usuario user = this.userRepo.getReferenceById(id);
        return new UserDTO(user);
    }

    public void updateUser(Usuario usuario) {
        userRepo.save(usuario);
    }

    @Transactional
    public void deleteUser(Long id){
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        }
    }
}
