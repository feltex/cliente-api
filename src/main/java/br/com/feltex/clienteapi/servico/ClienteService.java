package br.com.feltex.clienteapi.servico;

import br.com.feltex.clienteapi.controller.dto.ClienteRequest;
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

    public Cliente incluir(ClienteRequest clienteRequest) {
        var data = Instant.now();

        var cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setMatricula(clienteRequest.getMatricula());
        cliente.setTelefone(clienteRequest.getTelefone());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setFoto(clienteRequest.getFoto());
        cliente.setDataCadastro(data);
        cliente.setUltimaAtualizacao(data);
        clienteRepotirory.save(cliente);

        return cliente;
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
