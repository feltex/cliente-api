package br.com.feltex.clienteapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/upload", produces = {"application/json"})
@Slf4j
@CrossOrigin("*")
public class UploadArquivoController {


    @PostMapping("/arquivo")
    public ResponseEntity<String> salvarArquivo(@RequestParam("file") MultipartFile file) {
        log.info("Recebendo arquivo " + file.getOriginalFilename());
        var caminho = "/home/teco/temp/arquivos/" + UUID.randomUUID() + "." + extrairExtensao(file.getOriginalFilename());
        log.info("Novo nome será " + caminho);

        try {
            Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>("{ \"message\": \"Arquivo carregado com Sucesso!\"}", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Arquivo inválido.", e);
            return new ResponseEntity<>("{ \"message\":  \"Erro ao carregar o arquivo!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/arquivos")
    public void manipularMultiplosArquivos(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Recebendo arquivo " + file.getOriginalFilename());
        var caminho = "/home/teco/temp/arquivo.jpg";
        Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
    }


    private String extrairExtensao(final String nomeArquivo) {
        int i = nomeArquivo.lastIndexOf('.');
        return nomeArquivo.substring(i + 1);
    }

}
