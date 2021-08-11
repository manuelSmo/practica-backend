package com.pragma.cliente.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
public class ClientDto {

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
}
