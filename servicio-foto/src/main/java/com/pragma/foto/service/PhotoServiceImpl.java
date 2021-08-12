package com.pragma.foto.service;

import com.pragma.foto.dto.PhotoDto;
import com.pragma.foto.entity.Photo;
import com.pragma.foto.mapper.PhotoMapper;
import com.pragma.foto.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private final PhotoRepository photoRepository;


    @Override
    public List<PhotoDto> getPhoto() {

        List<PhotoDto> photosDto = new ArrayList<>();
        List<Photo> photos = photoRepository.findAll();
        if (!photos.isEmpty()){
            for (Photo photo : photos){
                PhotoDto photoDto = PhotoMapper.INSTANCE.photoToDto(photo);
                photosDto.add(photoDto);
            }
        }

        return photosDto;
    }

    @Override
    public PhotoDto createPhoto(PhotoDto photoDto) {

        PhotoDto photoDB = this.findByClientId(photoDto.getClientId());

        if (photoDB != null){
            return  photoDB;
        }

        Photo photo = PhotoMapper.INSTANCE.dtoToPhoto(photoDto);
        return PhotoMapper.INSTANCE.photoToDto(photoRepository.save(photo));
    }

    @Override
    public PhotoDto updatePhoto(PhotoDto photoDto) {

        Photo photo = PhotoMapper.INSTANCE.dtoToPhoto(this.findByClientId(photoDto.getClientId()));

        if (photo == null){
            return null;
        }

        photo.setPhoto(photoDto.getPhoto());
        return PhotoMapper.INSTANCE.photoToDto(photoRepository.save(photo));
    }

    @Override
    public void deletePhoto(Long id) {

        Photo photo = PhotoMapper.INSTANCE.dtoToPhoto(this.findByClientId(id));

        photoRepository.delete(photo);

    }

    @Override
    public PhotoDto findByClientId(Long id) {
        Photo photo = photoRepository.findByClientId(id);
        return PhotoMapper.INSTANCE.photoToDto(photo);
    }
}
