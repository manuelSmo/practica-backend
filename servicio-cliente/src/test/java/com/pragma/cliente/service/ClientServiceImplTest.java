package com.pragma.cliente.service;

import com.pragma.cliente.client.PhotoClient;
import com.pragma.cliente.entity.Client;
import com.pragma.cliente.model.ClientDto;
import com.pragma.cliente.model.PhotoDto;
import com.pragma.cliente.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private PhotoDto photoDto;

    @Mock
    private PhotoClient photoClient;

    @Mock
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        clientService = new ClientServiceImpl(clientRepository, photoClient);

        client = Client.builder()
                .firstName("Alejandro")
                .lastName("Hernandez")
                .numberId(1020785945L)
                .idType("Cedula")
                .age(25)
                .city("Bogota")
                .build();

        photoDto = PhotoDto.builder()
                .clientId(1020785945L)
                .photo("dgasfghfdhdshsdfh").build();

        List<Client> clientes = new ArrayList<>();
        clientes.add(client);

        Mockito.when(clientRepository.findById(1020785945L))
                .thenReturn(Optional.of(client));

        Mockito.when(photoClient.getPhotoByClientId(1020785945L))
                .thenReturn(ResponseEntity.ok(photoDto));

        Mockito.when(clientRepository.findAll())
                .thenReturn(clientes);

    }

    @Test
    void listAllClients() {

        Assertions.assertEquals(clientService.listAllClients().size(),1);

    }

    @Test
    void getClient() {
        ClientDto found = clientService.getClient(1020785945L);
        Assertions.assertEquals(found.getFirstName(), "Alejandro");
    }
}