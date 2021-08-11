package com.pragma.cliente.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.cliente.dto.ClientDto;

import com.pragma.cliente.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") long id){
        ClientDto encontrado = clientService.getClient(id);
        if (encontrado == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(encontrado);

    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto, BindingResult result){

        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        ClientDto clienteDB = clientService.createClient(clientDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") long id ,@RequestBody ClientDto clientDto){
        clientDto.setNumberId(id);
        ClientDto actual= clientService.updateClient(clientDto);

        if (actual == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientDto> deleteClient(@PathVariable("id") long id){

        clientService.deleteClient(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/filtros")
    public ResponseEntity<ClientDto> findByIdTypeAndNumberId(@RequestParam(name = "idType") String idType,
                                                             @RequestParam(name = "numberId")long id){
        ClientDto encontrado = clientService.findByIdTypeAndNumberId(idType,id);
        if (encontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrado);
    }

    @GetMapping("/mayores")
    public ResponseEntity<List<ClientDto>> findByIdTypeAndNumberId(@RequestParam(name = "age")Integer age){
        List<ClientDto> encontrados = clientService.findByAgeGreaterThanEqual(age);
        if (encontrados.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrados);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
