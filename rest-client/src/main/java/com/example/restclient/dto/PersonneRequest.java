package com.example.restclient.dto;

public record PersonneRequest(
        String nom,
        String prenom,
        int age
) {
}
