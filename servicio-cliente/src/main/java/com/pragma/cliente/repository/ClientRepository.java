package com.pragma.cliente.repository;


import com.pragma.cliente.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByIdTypeAndNumberId(String idType, Long numberId);
    List<Client> findByAgeGreaterThanEqual(Integer age);
}
