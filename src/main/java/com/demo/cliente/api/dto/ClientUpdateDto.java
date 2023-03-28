package com.demo.cliente.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateDto {
    private int id;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
}
