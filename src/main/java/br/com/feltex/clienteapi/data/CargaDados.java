package br.com.feltex.clienteapi.data;

import br.com.feltex.clienteapi.dao.ClienteRepotirory;
import br.com.feltex.clienteapi.modelo.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.UUID;

@Component
public class CargaDados {

    @Value("${habilitar.carregar.massa.dados}")
    private boolean podeCarregarDados;

    private final ClienteRepotirory clienteRepotirory;

    public CargaDados(ClienteRepotirory clienteRepotirory) {
        this.clienteRepotirory = clienteRepotirory;
    }

    @PostConstruct
    public void loadData() {

        if (podeCarregarDados) {
            for (int x = 1; x < 20; x++) {
                var cliente = new Cliente();
                var nome = UUID.randomUUID().toString();
                cliente.setNome(nome);
                cliente.setTelefone("99662-554" + x);
                cliente.setEmail(nome + "@feltex.com.br");
                cliente.setDataCadastro(Instant.now());
                cliente.setUltimaAtualizacao(Instant.now());
                clienteRepotirory.save(cliente);
            }

        }
    }
}
