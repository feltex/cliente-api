package br.com.feltex.clienteapi.servico;

import br.com.feltex.clienteapi.controller.dto.AtualizarClienteRequest;
import br.com.feltex.clienteapi.controller.dto.IncluirClienteRequest;
import br.com.feltex.clienteapi.dao.ClienteRepotirory;
import br.com.feltex.clienteapi.exception.ClienteNaoEncontradoException;
import br.com.feltex.clienteapi.modelo.Cliente;
import org.springframework.beans.BeanUtils;
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

    public Cliente incluir(IncluirClienteRequest clienteRequest) {
        var data = Instant.now();

        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteRequest, cliente);
        cliente.setDataCadastro(data);
        cliente.setUltimaAtualizacao(data);
        clienteRepotirory.save(cliente);

        return cliente;
    }

    public Cliente atualizar(AtualizarClienteRequest atualizarClienteRequest) {
        var cliente = clienteRepotirory.findById(atualizarClienteRequest.getId()).get();

        BeanUtils.copyProperties(atualizarClienteRequest, cliente);
        cliente.setUltimaAtualizacao(Instant.now());
        clienteRepotirory.save(cliente);
        return cliente;
    }

    public void deletar(Long id) {
        clienteRepotirory.deleteById(id);
    }
}
