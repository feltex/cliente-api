package br.com.feltex.clienteapi.controller;

import br.com.feltex.clienteapi.modelo.Cliente;
import br.com.feltex.clienteapi.servico.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> listar() {
        return new ResponseEntity<>(clienteService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> ler(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clienteService.getCliente(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Cliente> incluir(@RequestBody Cliente cliente) {
        clienteService.incluir(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
        var clienteAtualizado = clienteService.atualizar(cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
