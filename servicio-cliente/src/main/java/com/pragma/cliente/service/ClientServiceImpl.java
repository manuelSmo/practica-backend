package com.pragma.cliente.service;

import com.pragma.cliente.client.PhotoClient;
import com.pragma.cliente.entity.Client;
import com.pragma.cliente.mapper.ClientMapper;
import com.pragma.cliente.model.ClientDto;
import com.pragma.cliente.model.PhotoDto;
import com.pragma.cliente.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    PhotoClient photoClient;

    @Override
    public List<ClientDto> listAllClients() {

        List<ClientDto> clientesDto = new ArrayList<>();
        List<Client> clients = clientRepository.findAll();

        if (!clients.isEmpty()) {
            for (Client client : clients) {
                ClientDto encontrado = ClientMapper.INSTANCE.clientToDto(client);
                PhotoDto photoDto = photoClient.getPhotoByClientId(encontrado.getNumberId()).getBody();
                encontrado.setPhotoDto(photoDto);
                clientesDto.add(encontrado);
            }
        }
        return clientesDto;
    }

    @Override
    public ClientDto getClient(Long numberId) {
        Client encontrado = clientRepository.findById(numberId).orElse(null);

        if (encontrado != null) {
            PhotoDto photoDto = photoClient.getPhotoByClientId(numberId).getBody();
            if (photoDto != null) {
                encontrado.setPhotoDto(photoDto);
            }
            return ClientMapper.INSTANCE.clientToDto(encontrado);
        }
        return null;
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        ClientDto clienteDB = this.getClient(clientDto.getNumberId());

        if (clienteDB != null) {
            return clienteDB;
        }
        Client actual = ClientMapper.INSTANCE.dtoToClient(clientDto);
        PhotoDto photoDto = clientDto.getPhotoDto();

        if (photoDto != null) {
            PhotoDto photo = photoClient.createPhoto(photoDto).getBody();
            clientDto = ClientMapper.INSTANCE.clientToDto(clientRepository.save(actual));
            clientDto.setPhotoDto(photo);
            return clientDto;
        }

        clientDto = ClientMapper.INSTANCE.clientToDto(clientRepository.save(actual));
        return clientDto;
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {

        Client cliente = ClientMapper.INSTANCE.dtoToClient(getClient(clientDto.getNumberId()));
        if (cliente == null) {
            return null;
        }

        cliente.setFirstName(clientDto.getFirstName());
        cliente.setLastName(clientDto.getLastName());
        cliente.setIdType(clientDto.getIdType());
        cliente.setAge(clientDto.getAge());
        cliente.setCity(clientDto.getCity());
        PhotoDto photoDto = clientDto.getPhotoDto();

        if (photoDto != null) {
            PhotoDto photo = photoClient.updatePhoto(photoDto).getBody();
            ClientDto creado = ClientMapper.INSTANCE.clientToDto(clientRepository.save(cliente));
            creado.setPhotoDto(photo);
            return creado;
        }
        clientDto = ClientMapper.INSTANCE.clientToDto(clientRepository.save(cliente));
        return clientDto;
    }

    @Override
    public ClientDto deleteClient(Long numberId) {

        Client cliente = clientRepository.findById(numberId).orElse(null);
        if (cliente == null) {
            return null;
        }
        PhotoDto photoDto = photoClient.getPhotoByClientId(numberId).getBody();
        if (photoDto != null) {
            photoClient.deletePhoto(photoDto.getClientId());
            cliente.setPhotoDto(photoDto);
        }
        clientRepository.delete(cliente);
        return ClientMapper.INSTANCE.clientToDto(cliente);
    }

    @Override
    public ClientDto findByIdTypeAndNumberId(String idType, Long numberId) {
        Client encontrado = clientRepository.findByIdTypeAndNumberId(idType, numberId);

        if (encontrado != null) {
            PhotoDto photoDto = photoClient.getPhotoByClientId(numberId).getBody();
            if (photoDto != null) {
                encontrado.setPhotoDto(photoDto);
            }
            return ClientMapper.INSTANCE.clientToDto(encontrado);
        }
        return null;

    }

    @Override
    public List<ClientDto> findByAgeGreaterThanEqual(Integer age) {

        List<ClientDto> clientesDto = new ArrayList<>();
        List<Client> clientes = clientRepository.findByAgeGreaterThanEqual(age);
        if (!clientes.isEmpty()) {
            for (Client client : clientes) {
                ClientDto encontrado = ClientMapper.INSTANCE.clientToDto(client);
                PhotoDto photoDto = photoClient.getPhotoByClientId(encontrado.getNumberId()).getBody();
                encontrado.setPhotoDto(photoDto);
                clientesDto.add(encontrado);
            }
        }
        return clientesDto;
    }

}
