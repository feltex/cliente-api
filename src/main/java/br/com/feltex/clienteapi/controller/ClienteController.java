package br.com.feltex.clienteapi.controller;

import br.com.feltex.clienteapi.controller.dto.AtualizarClienteRequest;
import br.com.feltex.clienteapi.controller.dto.IncluirClienteRequest;
import br.com.feltex.clienteapi.controller.dto.IncluirClienteResponse;
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
    public ResponseEntity<IncluirClienteResponse> incluir(@RequestParam String clienteData, @RequestParam("file") final MultipartFile file) throws IOException {

        final var incluirClienteRequest = mapper.readValue(clienteData, IncluirClienteRequest.class);
        incluirClienteRequest.setFoto(file.getInputStream().readAllBytes());

        var cliente = clienteService.incluir(incluirClienteRequest);

        var clienteResponse = new IncluirClienteResponse();
        BeanUtils.copyProperties(cliente, clienteResponse);
        return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Cliente> atualizar(@RequestParam String clienteData, @RequestParam(value = "file", required = false) final MultipartFile file ) throws IOException {
        final var atualizarClienteRequest = mapper.readValue(clienteData, AtualizarClienteRequest.class);

        if (file != null){
            atualizarClienteRequest.setFoto(file.getInputStream().readAllBytes());
        }

        var cliente = clienteService.atualizar(atualizarClienteRequest);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
