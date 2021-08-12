package com.pragma.foto.dto;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class PhotoDto {

    private Long clientId;

    private String photo;
}
