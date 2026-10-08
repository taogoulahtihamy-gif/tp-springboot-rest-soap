package com.example.restclient.dto;

public record PersonneDto(
        Long id,
        String nom,
        String prenom,
        int age
) {
}
