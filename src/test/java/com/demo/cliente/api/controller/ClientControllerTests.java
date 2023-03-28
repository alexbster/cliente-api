package com.demo.cliente.api.controller;

import com.demo.cliente.api.dto.ClientDto;
import com.demo.cliente.api.dto.ClientUpdateDto;
import com.demo.cliente.api.dto.ClientUpdateStatusDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTests {
    private static final String API_ROOT = "http://localhost";
    private static final String API_RESOURCE_ALL = "/v1/api/clients";
    private static final String API_RESOURCE_SEARCH = "/v1/api/clients/search?identification=";
    private static final String API_RESOURCE_CREATE = "/v1/api/clients";
    private static final String API_RESOURCE_CONTACT = "/v1/api/clients/contact";
    private static final String API_RESOURCE_STATUS = "/v1/api/clients/status";
    private WebTestClient webTestClient;

    @LocalServerPort
    private int port = 0;

    @BeforeEach
    public void setup() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl(API_ROOT + ":" + port)
                .responseTimeout(Duration.ofSeconds(10))
                .build();
    }

    @Test
    void getClients() {
        var clientes = webTestClient
                .get()
                .uri(API_RESOURCE_ALL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ClientDto.class)
                .returnResult()
                .getResponseBody();
        assert clientes.size() > 0;
    }

    @Test
    void findClientsByIdentificationContainingIgnoreCase() {
        var clientes = webTestClient
                .get()
                .uri(API_RESOURCE_SEARCH + "123")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ClientDto.class)
                .returnResult()
                .getResponseBody();
        assert clientes.size() > 0;
    }

    @Test
    void save() {
        var clientes = webTestClient
                .post()
                .uri(API_RESOURCE_CREATE)
                .bodyValue(new ClientDto(0, "Bruce", "Wayne", "123456789", "ImBatman@domain.com", "5007000989", "Street 123", "Gotham City", "New York", "USA", "CR", true))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ClientDto.class)
                .returnResult()
                .getResponseBody();
        assert clientes.size() > 0;
    }

    @Test
    void update() {
        var clientes = webTestClient
                .put()
                .uri(API_RESOURCE_CONTACT)
                .bodyValue(new ClientUpdateDto(1, "5007000989", "Street 123", "Gotham City", "New York", "USA"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ClientDto.class)
                .returnResult()
                .getResponseBody();
        assert clientes.size() > 0;
    }

    @Test
    void updateStatus() {
        var clientes = webTestClient
                .put()
                .uri(API_RESOURCE_STATUS)
                .bodyValue(new ClientUpdateStatusDto(1, false))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ClientDto.class)
                .returnResult()
                .getResponseBody();
        assert clientes.size() > 0;
    }


    @Test
    void badRequest() {
        var errors = webTestClient
                .post()
                .uri(API_RESOURCE_CREATE)
                .bodyValue(new ClientDto(0, null, null, "123456789", "ImBatman@domain.com", "5007000989", "Street 123", "Gotham City", "New York", "USA", null, true))
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBodyList(String.class)
                .returnResult()
                .getResponseBody();
        assert errors.size() == 1;
    }

}
