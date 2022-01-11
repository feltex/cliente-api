package br.com.feltex.clienteapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncluirClienteRequest {
    private String nome;
    private String telefone;
    private String matricula;
    private String email;
    private byte[] foto;
}
