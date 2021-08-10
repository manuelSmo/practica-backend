package com.pragma.cliente.mapper;

import com.pragma.cliente.dto.ClientDto;
import com.pragma.cliente.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto clientToDto(Client client);
    Client dtoToClient(ClientDto clientDto);
}
