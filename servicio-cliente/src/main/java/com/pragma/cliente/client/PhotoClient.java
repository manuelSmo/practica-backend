package com.pragma.cliente.client;

import com.pragma.cliente.dto.PhotoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "servicio-foto")
@RequestMapping(value = "/photos")
public interface PhotoClient {

    @GetMapping(value = "/{id}")
    public ResponseEntity<PhotoDto> getPhotoByClientId(@PathVariable("id") Long id);
}
