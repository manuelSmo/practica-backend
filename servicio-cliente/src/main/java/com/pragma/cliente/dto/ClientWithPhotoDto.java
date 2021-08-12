package com.pragma.cliente.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class ClientWithPhotoDto {

    @Id
    private Long numberId;

    @NotEmpty(message = "El nombre no debe ser vacío")
    private String firstName;

    @NotEmpty(message = "El apellido no debe ser vacío")
    private String lastName;

    @NotEmpty(message = "El tipo de id no debe ser vacío")
    private String idType;

    private Integer age;

    @NotEmpty(message = "La ciudad no debe ser vacía")
    private String city;

    private Long clientId;

    private String photo;
}
