package com.pragma.foto.mapper;

import com.pragma.foto.dto.PhotoDto;
import com.pragma.foto.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    PhotoDto photoToDto(Photo photo);
    Photo dtoToPhoto(PhotoDto photoDto);
}
