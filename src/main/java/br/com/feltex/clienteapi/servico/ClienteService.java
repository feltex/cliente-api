package br.com.feltex.clienteapi.servico;

import br.com.feltex.clienteapi.dao.ClienteRepotirory;
import br.com.feltex.clienteapi.exception.ClienteNaoEncontradoException;
import br.com.feltex.clienteapi.modelo.Cliente;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepotirory clienteRepotirory;

    public ClienteService(ClienteRepotirory clienteRepotirory) {
        this.clienteRepotirory = clienteRepotirory;
    }

    public List<Cliente> listar() {
        return clienteRepotirory.findAll();
    }

    public Cliente getCliente(Long id) {
        return clienteRepotirory.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado " + id));
    }

    public void incluir(Cliente cliente) {
        var data = Instant.now();
        cliente.setDataCadastro(data);
        cliente.setUltimaAtualizacao(data);
        clienteRepotirory.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        var clienteAtualizado = clienteRepotirory.findById(cliente.getId()).get();

        clienteAtualizado.setNome(cliente.getNome());
        clienteAtualizado.setTelefone(cliente.getTelefone());
        clienteAtualizado.setEmail(cliente.getEmail());
        clienteAtualizado.setUltimaAtualizacao(Instant.now());
        clienteRepotirory.save(clienteAtualizado);
        return clienteAtualizado;
    }

    public void deletar(Long id) {
        clienteRepotirory.deleteById(id);
    }
}
