package com.pragma.foto.service;


import com.pragma.foto.dto.PhotoDto;
import com.pragma.foto.entity.Photo;

import java.util.List;

public interface PhotoService {

    List<PhotoDto> getPhoto();

    PhotoDto createPhoto(PhotoDto photoDto);

    PhotoDto updatePhoto(PhotoDto photoDto);

    void deletePhoto(Long id);

    PhotoDto findByClientId(Long id);
}
