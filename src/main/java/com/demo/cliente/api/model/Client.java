package com.demo.cliente.api.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String lastName;

    @Column
    @NotBlank
    private String identification;

    @Column
    @NotBlank
    private String email;

    @Column
    @NotBlank
    private String phone;

    @Column
    @NotBlank
    private String address;

    @Column
    @NotBlank
    private String city;

    @Column
    @NotBlank
    private String state;

    @Column
    @NotBlank
    private String country;

    @Column
    @NotBlank
    private String countryOfBirth;

    @Column
    @NotNull
    private boolean active;
}
