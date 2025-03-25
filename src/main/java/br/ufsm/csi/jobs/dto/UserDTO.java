package br.ufsm.csi.jobs.dto;

import br.ufsm.csi.jobs.model.Usuario;

import java.util.Set;


public record UserDTO(Long id, String email, Set<String> roles) {
    public UserDTO(Usuario user) {
        this(user.getId(), user.getEmail(), user.getRoles());
    }
}
