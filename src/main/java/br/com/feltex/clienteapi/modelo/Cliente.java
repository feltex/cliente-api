package br.com.feltex.clienteapi.modelo;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String telefone;
    @Column(unique = true)
    private String email;
    private String urlImagem;
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;

}
