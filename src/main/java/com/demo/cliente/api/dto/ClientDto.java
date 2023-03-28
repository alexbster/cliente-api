package com.demo.cliente.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String identification;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String countryOfBirth;

    private boolean active;
}
