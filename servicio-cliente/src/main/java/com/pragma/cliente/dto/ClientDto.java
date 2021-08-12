package com.pragma.cliente.dto;

import com.pragma.cliente.model.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    private PhotoDto photoDto;

}
