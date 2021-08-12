package com.pragma.cliente.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Photo {

    private Long clientId;

    private String photo;
}
