package br.ufsm.csi.jobs.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Vaga {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Campo obrigatório!")
    private String titulo;
    @NotBlank
    private String descricao;
    private String requisitos;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
