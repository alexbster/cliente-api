package com.demo.cliente.api.service;

import com.demo.cliente.api.dto.ClientDto;
import com.demo.cliente.api.dto.ClientUpdateDto;
import com.demo.cliente.api.dto.ClientUpdateStatusDto;
import com.demo.cliente.api.model.Client;
import com.demo.cliente.api.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Obtiene todos los clientes
     * @return
     */
    public List<ClientDto> getClients() {
        List<ClientDto> result = new ArrayList<>();
        clientRepository.getClients().forEach(entity ->
                {
                    result.add(this.copy(entity, new ClientDto()));
                }
        );
        return result;
    }

    /**
     * Obtiene los clientes por identificación
     * @param identification
     * @return
     */
    public List<ClientDto> findClientsByIdentificationContainingIgnoreCase(String identification) {
        List<ClientDto> result = new ArrayList<>();
        clientRepository.findClientsByIdentificationContainingIgnoreCase(identification).forEach(entity ->
                {
                    result.add(this.copy(entity, new ClientDto()));
                }
        );
        return result;
    }

    /**
     * Guarda un cliente
     * @param clientDto
     * @return
     */
    public ClientDto save(ClientDto clientDto) {
        var instance = this.copy(clientDto, new Client());
        clientRepository.save(instance);
        clientDto.setId(instance.getId());
        return clientDto;
    }

    /**
     * Actualiza un cliente
     * @param clientUpdateDto
     * @return
     */
    public ClientDto update(ClientUpdateDto clientUpdateDto) {
        var instance = clientRepository.findClientById(clientUpdateDto.getId());
        if(instance == null)
            return null;

        if(clientUpdateDto.getPhone() != null)
            instance.setPhone(clientUpdateDto.getPhone());
        if(clientUpdateDto.getAddress() != null)
            instance.setAddress(clientUpdateDto.getAddress());
        if(clientUpdateDto.getCity() != null)
            instance.setCity(clientUpdateDto.getCity());
        if(clientUpdateDto.getState() != null)
            instance.setState(clientUpdateDto.getState());
        if(clientUpdateDto.getCountry() != null)
            instance.setCountry(clientUpdateDto.getCountry());

        clientRepository.save(instance);

        return this.copy(instance, new ClientDto());
    }

    /**
     * Cambia el estado de un cliente
     * @param clientUpdateStatusDto
     * @return
     */
    public ClientDto changeStatus(ClientUpdateStatusDto clientUpdateStatusDto) {
        var instance = clientRepository.findClientById(clientUpdateStatusDto.getId());
        if(instance == null)
            return null;

        instance.setActive(clientUpdateStatusDto.isActive());

        clientRepository.save(instance);

        return this.copy(instance, new ClientDto());
    }

    /**
     * Copia los valores de un objeto a otro
     * @param origen
     * @param destino
     * @param <S> Tipo origen
     * @param <T> Tipo destino
     * @return
     */
    private <S,T> T copy(S origen, T destino){
        if(origen == null || destino == null)
            return null;
        BeanUtils.copyProperties(origen, destino);
        return destino;
    }
}
