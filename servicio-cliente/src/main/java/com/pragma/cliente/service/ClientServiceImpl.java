package com.pragma.cliente.service;

import com.pragma.cliente.dto.ClientDto;
import com.pragma.cliente.entity.Client;
import com.pragma.cliente.mapper.ClientMapper;
import com.pragma.cliente.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    @Autowired
    private final ClientRepository clientRepository;


    @Override
    public List<ClientDto> listAllClients() {

        List<ClientDto> clientesDto = new ArrayList<>();
        List<Client> clients = clientRepository.findAll();

        if (!clients.isEmpty()){
            for (Client client : clients){
                ClientDto encontrado = ClientMapper.INSTANCE.clientToDto(client);
                clientesDto.add(encontrado);
            }
        }
        return clientesDto;
    }

    @Override
    public ClientDto getClient(Long numberId) {
        return null;
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {

        Client actual = ClientMapper.INSTANCE.dtoToClient(clientDto);

        return ClientMapper.INSTANCE.clientToDto(clientRepository.save(actual));
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        return null;
    }

    @Override
    public ClientDto deleteClient(Long numberId) {
        return null;
    }

    @Override
    public ClientDto findByIdTypeAndNumberId(String idType, Long numberId) {
        return null;
    }

    @Override
    public ClientDto findByAgeGreaterThanEqual(Integer age) {
        return null;
    }

}
