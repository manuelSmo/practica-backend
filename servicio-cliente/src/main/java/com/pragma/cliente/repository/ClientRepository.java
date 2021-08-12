package com.pragma.cliente.repository;


import com.pragma.cliente.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByIdTypeAndNumberId(String idType, Long numberId);
    List<Client> findByAgeGreaterThanEqual(Integer age);
}
