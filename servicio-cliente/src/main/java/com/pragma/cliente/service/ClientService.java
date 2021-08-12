package com.pragma.cliente.service;

import com.pragma.cliente.dto.ClientDto;
import com.pragma.cliente.dto.ClientWithPhotoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

     List<ClientDto> listAllClients();

     ClientDto getClient(Long numberId);

    ClientWithPhotoDto getClientWithPhoto(Long numberId);

    ClientDto createClient(ClientDto clientDto);

     ClientDto updateClient(ClientDto clientDto);

     ClientDto deleteClient(Long numberId);

     ClientDto findByIdTypeAndNumberId(String idType, Long numberId);

     List<ClientDto> findByAgeGreaterThanEqual(Integer age);
}
