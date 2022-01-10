package br.com.feltex.clienteapi.controller;

import br.com.feltex.clienteapi.controller.dto.ClienteRequest;
import br.com.feltex.clienteapi.controller.dto.ClienteResponse;
import br.com.feltex.clienteapi.modelo.Cliente;
import br.com.feltex.clienteapi.servico.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ObjectMapper mapper = new ObjectMapper();

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
    public ResponseEntity<ClienteResponse> incluir(@RequestParam String clienteData, @RequestParam("file") final MultipartFile file) throws IOException {

        final var cliente = mapper.readValue(clienteData, ClienteRequest.class);
        cliente.setFoto(file.getInputStream().readAllBytes());

        var clienteIncluido = clienteService.incluir(cliente);
        var clienteResponse = new ClienteResponse();
        BeanUtils.copyProperties(clienteIncluido, clienteResponse);
        return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
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
