package br.com.feltex.clienteapi.servico;

import br.com.feltex.clienteapi.controller.dto.AtualizarClienteRequest;
import br.com.feltex.clienteapi.controller.dto.IncluirClienteRequest;
import br.com.feltex.clienteapi.dao.ClienteRepository;
import br.com.feltex.clienteapi.exception.ClienteNaoEncontradoException;
import br.com.feltex.clienteapi.modelo.Cliente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado " + id));
    }

    public Cliente incluir(IncluirClienteRequest clienteRequest) {
        var data = Instant.now();

        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteRequest, cliente);
        cliente.setDataCadastro(data);
        cliente.setUltimaAtualizacao(data);
        clienteRepository.save(cliente);

        return cliente;
    }

    public Cliente atualizar(AtualizarClienteRequest atualizarClienteRequest) {
        var cliente = clienteRepository.findById(atualizarClienteRequest.getId()).get();

        BeanUtils.copyProperties(atualizarClienteRequest, cliente);
        cliente.setUltimaAtualizacao(Instant.now());
        clienteRepository.save(cliente);
        return cliente;
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
