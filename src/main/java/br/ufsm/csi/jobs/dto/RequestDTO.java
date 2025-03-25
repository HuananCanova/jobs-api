package br.ufsm.csi.jobs.dto;

import java.util.Set;

public record RequestDTO(String email, String senha, Set<String> roles){
}
