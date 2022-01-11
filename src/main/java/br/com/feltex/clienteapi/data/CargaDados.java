package br.com.feltex.clienteapi.data;

import br.com.feltex.clienteapi.dao.ClienteRepository;
import br.com.feltex.clienteapi.modelo.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Component
public class CargaDados {

    @Value("${habilitar.carregar.massa.dados}")
    private boolean podeCarregarDados;

    private final ClienteRepository clienteRepotirory;

    public CargaDados(ClienteRepository clienteRepotirory) {
        this.clienteRepotirory = clienteRepotirory;
    }

    @PostConstruct
    public void loadData() throws IOException {

        if (podeCarregarDados) {
            for (int x = 1; x <= 8; x++) {

                var foto = getClass()
                        .getClassLoader()
                        .getResourceAsStream("clientes/avatar" + x + ".png")
                        .readAllBytes();

                var cliente = new Cliente();
                var nome = UUID.randomUUID().toString();
                cliente.setId(Long.valueOf(x));
                cliente.setNome(nome);
                cliente.setTelefone("99662-554" + x);
                cliente.setEmail(nome + "@feltex.com.br");
                cliente.setFoto(foto);
                cliente.setDataCadastro(Instant.now());
                cliente.setUltimaAtualizacao(Instant.now());
                clienteRepotirory.save(cliente);
            }

        }
    }
}
