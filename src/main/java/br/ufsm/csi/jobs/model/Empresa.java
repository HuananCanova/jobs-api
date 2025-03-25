package br.ufsm.csi.jobs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Empresa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "razao_social")
    @NotBlank
    private String razaoSocial;
    @Size(max = 14, message = "máximo de 14 dígitos - CNPJ")
    @NotBlank
    private String CNPJ;
    @Email
    private String email;
    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}
