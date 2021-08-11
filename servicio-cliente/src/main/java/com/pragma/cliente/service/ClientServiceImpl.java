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
        Client encontrado = clientRepository.findById(numberId).orElse(null);

        return ClientMapper.INSTANCE.clientToDto(encontrado);

    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {

        ClientDto clienteDB = this.getClient(clientDto.getNumberId());

        if (clienteDB != null){
            return clienteDB;
        }
        Client actual = ClientMapper.INSTANCE.dtoToClient(clientDto);

        return ClientMapper.INSTANCE.clientToDto(clientRepository.save(actual));
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {

        Client cliente = ClientMapper.INSTANCE.dtoToClient(getClient(clientDto.getNumberId()));
        if (cliente == null){
            return null;
        }

        cliente.setFirstName(clientDto.getFirstName());
        cliente.setLastName(clientDto.getLastName());
        cliente.setIdType(clientDto.getIdType());
        cliente.setAge(clientDto.getAge());
        cliente.setCity(clientDto.getCity());

        return ClientMapper.INSTANCE.clientToDto(clientRepository.save(cliente));
    }

    @Override
    public ClientDto deleteClient(Long numberId) {

        Client cliente = clientRepository.findById(numberId).orElse(null);
        if (cliente == null){
            return null;
        }
        clientRepository.delete(cliente);
        return ClientMapper.INSTANCE.clientToDto(cliente);

    }

    @Override
    public ClientDto findByIdTypeAndNumberId(String idType, Long numberId) {
        Client encontrado = clientRepository.findByIdTypeAndNumberId(idType,numberId);
        return ClientMapper.INSTANCE.clientToDto(encontrado);
    }

    @Override
    public List<ClientDto> findByAgeGreaterThanEqual(Integer age) {

        List<ClientDto> clientesDto = new ArrayList<>();
        List<Client> clientes= clientRepository.findByAgeGreaterThanEqual(age);
        if (!clientes.isEmpty()){
            for (Client client : clientes){
                ClientDto encontrado = ClientMapper.INSTANCE.clientToDto(client);
                clientesDto.add(encontrado);
            }
        }
        return clientesDto;
    }

}
