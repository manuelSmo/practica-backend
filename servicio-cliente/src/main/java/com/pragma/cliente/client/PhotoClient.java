package com.pragma.cliente.client;

import com.pragma.cliente.model.PhotoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "servicio-foto", fallback = PhotoHystrixFallbackFactory.class)
public interface PhotoClient {

    @GetMapping(value = "/photos/{id}")
    public ResponseEntity<PhotoDto> getPhotoByClientId(@PathVariable("id") Long id);

    @PostMapping(value = "/photos")
    public ResponseEntity<PhotoDto> createPhoto(@Valid @RequestBody PhotoDto photoDto);

    @DeleteMapping(value = "/photos/{id}")
    public ResponseEntity<PhotoDto> deletePhoto(@PathVariable("id") Long id);

    @PutMapping(value = "/photos")
    public ResponseEntity<PhotoDto> updatePhoto(@RequestBody PhotoDto photoDto);
}
