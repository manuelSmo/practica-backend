package com.pragma.cliente.dto;

import lombok.Data;

import javax.persistence.Id;

@Data
public class ClientDto {

    @Id
    private Long numberId;
    private String firstName;
    private String lastName;
    private String idType;
    private Integer age;
    private String city;
}
