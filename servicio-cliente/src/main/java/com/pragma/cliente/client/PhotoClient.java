package com.pragma.cliente.client;

import com.pragma.cliente.dto.PhotoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "servicio-foto")
@RequestMapping(value = "/photos")
public interface PhotoClient {

    @GetMapping(value = "/{id}")
    public ResponseEntity<PhotoDto> getPhotoByClientId(@PathVariable("id") Long id);

    @PostMapping
    public ResponseEntity<PhotoDto> createPhoto(@Valid @RequestBody PhotoDto photoDto);
}
