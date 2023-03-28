package com.demo.cliente.api.repository;

import com.demo.cliente.api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer>
        , JpaSpecificationExecutor<Client> {

    @Query("SELECT c FROM Client c")
    public List<Client> getClients();
    public Client findClientById(int id);
    public List<Client> findClientsByIdentificationContainingIgnoreCase(String identification);
}
