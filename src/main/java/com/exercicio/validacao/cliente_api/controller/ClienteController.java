package com.exercicio.validacao.cliente_api.controller;

import com.exercicio.validacao.cliente_api.dto.ClienteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PostMapping
    public ResponseEntity<String> criarCliente(@RequestBody ClienteRequest request){

        System.out.println("Cliente recebido com sucesso " + request);

        return ResponseEntity.ok("Cliente criado com sucesso");
    }
}
