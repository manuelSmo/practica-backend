package com.pragma.cliente.client;

import com.pragma.cliente.model.PhotoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PhotoHystrixFallbackFactory implements PhotoClient {

    @Override
    public ResponseEntity<PhotoDto> getPhotoByClientId(Long id) {
        PhotoDto photoDto = PhotoDto.builder()
                .clientId(0L)
                .photo("none")
                .build();
        return ResponseEntity.ok(photoDto);
    }

    @Override
    public ResponseEntity<PhotoDto> createPhoto(PhotoDto photoDto) {
        PhotoDto photo = PhotoDto.builder()
                .clientId(0L)
                .photo("none")
                .build();
        return ResponseEntity.ok(photo);
    }

    @Override
    public ResponseEntity<PhotoDto> deletePhoto(Long id) {
        PhotoDto photo = PhotoDto.builder()
                .clientId(0L)
                .photo("none")
                .build();
        return ResponseEntity.ok(photo);
    }

    @Override
    public ResponseEntity<PhotoDto> updatePhoto(PhotoDto photoDto) {
        PhotoDto photo = PhotoDto.builder()
                .clientId(0L)
                .photo("none")
                .build();
        return ResponseEntity.ok(photo);
    }
}
