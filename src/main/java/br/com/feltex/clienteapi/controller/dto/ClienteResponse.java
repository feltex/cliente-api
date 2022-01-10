package br.com.feltex.clienteapi.controller.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ClienteResponse {
    private Long id;
    private String nome;
    private String telefone;
    private String matricula;
    private String email;
    private byte[] foto;
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;
}
