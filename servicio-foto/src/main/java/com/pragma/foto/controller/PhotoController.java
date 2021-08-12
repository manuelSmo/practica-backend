package com.pragma.foto.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.foto.dto.PhotoDto;
import com.pragma.foto.entity.Photo;
import com.pragma.foto.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @GetMapping
    public ResponseEntity<List<PhotoDto>> getPhotos(){
        List<PhotoDto> photos= photoService.getPhoto();
        if (photos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(photos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PhotoDto> getPhotoByClientId(@PathVariable("id") Long id){
        PhotoDto photoDto = photoService.findByClientId(id);
        if (photoDto == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(photoDto);

    }

    @PostMapping
    public ResponseEntity<PhotoDto> createPhoto(@Valid @RequestBody PhotoDto photoDto){

        PhotoDto photoDB = photoService.createPhoto(photoDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(photoDB);
    }

    @PutMapping
    public ResponseEntity<PhotoDto> updatePhoto(@RequestBody PhotoDto photoDto){
        //photoDto.setClientId(id);
        PhotoDto actual= photoService.updatePhoto(photoDto);

        if (actual == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PhotoDto> deletePhoto(@PathVariable("id") Long id){

        photoService.deletePhoto(id);

        return ResponseEntity.ok().build();
    }



}
