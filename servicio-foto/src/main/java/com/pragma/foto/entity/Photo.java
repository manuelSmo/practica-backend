package com.pragma.foto.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Photos")
@Data
public class Photo {

    @Id
    private Long clientId;

    private String photo;
}
