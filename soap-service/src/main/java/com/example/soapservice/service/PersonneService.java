package com.example.soapservice.service;

import com.example.soapservice.entity.Personne;
import com.example.soapservice.repository.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {

    private final PersonneRepository repository;

    public PersonneService(PersonneRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Personne ajouter(String nom, String prenom, int age) {
        Personne personne = new Personne(nom, prenom, age);
        return repository.save(personne);
    }

    // READ
    public Optional<Personne> rechercher(Long id) {
        return repository.findById(id);
    }

    // READ ALL
    public List<Personne> lister() {
        return repository.findAll();
    }

    // UPDATE
    public Optional<Personne> modifier(Long id, String nom,
                                       String prenom, int age) {

        return repository.findById(id).map(personne -> {
            personne.setNom(nom);
            personne.setPrenom(prenom);
            personne.setAge(age);
            return repository.save(personne);
        });
    }

    // DELETE
    public boolean supprimer(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
