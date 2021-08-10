package com.pragma.cliente.controller;

import com.pragma.cliente.dto.ClientDto;
import com.pragma.cliente.entity.Client;
import com.pragma.cliente.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/clientes")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> listClients(){
        List<ClientDto> clientes= clientService.listAllClients();
        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }


    @PostMapping
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto){

        ClientDto clienteDB = clientService.createClient(clientDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDB);
    }

}
