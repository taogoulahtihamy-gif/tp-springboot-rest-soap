package com.example.restclient.controller;

import com.example.restclient.client.PersonneSoapClient;
import com.example.restclient.dto.PersonneDto;
import com.example.restclient.dto.PersonneRequest;
import com.example.restclient.generated.Personne;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final PersonneSoapClient soapClient;

    public PersonneController(PersonneSoapClient soapClient) {
        this.soapClient = soapClient;
    }

    // Conversion SOAP vers JSON
    private PersonneDto convertir(Personne personne) {
        return new PersonneDto(
                personne.getId(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getAge()
        );
    }

    // GET ALL : Lister toutes les personnes
    @GetMapping
    public List<PersonneDto> lister() {

        return soapClient.lister()
                .getPersonne()
                .stream()
                .map(this::convertir)
                .toList();
    }

    // GET : Recuperer une personne
    @GetMapping("/{id}")
    public ResponseEntity<PersonneDto> rechercher(
            @PathVariable Long id) {

        Personne personne = soapClient
                .rechercher(id)
                .getPersonne();

        if (personne == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(convertir(personne));
    }

    // POST : Creer une personne
    @PostMapping
    public ResponseEntity<PersonneDto> ajouter(
            @RequestBody PersonneRequest request) {

        Personne personne = soapClient.ajouter(
                request.nom(),
                request.prenom(),
                request.age()
        ).getPersonne();

        if (personne == null) {
            return ResponseEntity.status(
                    HttpStatus.BAD_GATEWAY
            ).build();
        }

        return ResponseEntity
                .created(URI.create("/api/personnes/" + personne.getId()))
                .body(convertir(personne));
    }

    // PUT : Modifier une personne
    @PutMapping("/{id}")
    public ResponseEntity<PersonneDto> modifier(
            @PathVariable Long id,
            @RequestBody PersonneRequest request) {

        Personne personne = soapClient.modifier(
                id,
                request.nom(),
                request.prenom(),
                request.age()
        ).getPersonne();

        if (personne == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(convertir(personne));
    }

    // DELETE : Supprimer une personne
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id) {

        boolean success = soapClient
                .supprimer(id)
                .isSuccess();

        if (!success) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
