package com.pragma.cliente.entity;

import com.pragma.cliente.model.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "CLIENTE")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Client {

    @Id
    private Long numberId;

    private String firstName;

    private String lastName;

    private String idType;

    private Integer age;

    private String city;

    @Transient
    private Photo photo;


}
