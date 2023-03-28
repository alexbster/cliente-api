package com.demo.cliente.api.controller;

import com.demo.cliente.api.dto.ClientDto;
import com.demo.cliente.api.dto.ClientUpdateDto;
import com.demo.cliente.api.dto.ClientUpdateStatusDto;
import com.demo.cliente.api.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/clients")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;


    /**
     * Obtiene todos los clientes
     * @implNote Code challenge punto 1
     * @return
     */
    @GetMapping()
    public List<ClientDto> getClients() {
        return clientService.getClients();
    }

    /**
     * Obtiene los clientes por identificación
     * @implNote Code challenge punto 2
     * @param identification
     * @return
     */
    @GetMapping("/search")
    public List<ClientDto> findClientsByIdentificationContainingIgnoreCase(@RequestParam String identification) {
        return clientService.findClientsByIdentificationContainingIgnoreCase(identification);
    }

    /**
     * Guarda un cliente
     * @implNote Code challenge punto 3
     * @param clientDto
     * @return
     */
    @PostMapping
    public ClientDto save(@RequestBody ClientDto clientDto) {
        return clientService.save(clientDto);
    }

    /**
     * Actualiza un cliente
     * @implNote Code challenge punto 4
     * @param clientUpdateDto
     * @return
     */
    @PutMapping("/contact")
    public ClientDto update(@RequestBody ClientUpdateDto clientUpdateDto) {
        return clientService.update(clientUpdateDto);
    }

    /**
     * Actualiza el estado de un cliente
     * @implNote Code challenge punto 5
     * @param clientUpdateStatusDto
     * @return
     */
    @PutMapping("/status")
    public ClientDto updateStatus(@RequestBody ClientUpdateStatusDto clientUpdateStatusDto) {
        return clientService.changeStatus(clientUpdateStatusDto);
    }

}
