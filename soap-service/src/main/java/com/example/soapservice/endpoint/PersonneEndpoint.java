package com.example.soapservice.endpoint;

import com.example.soapservice.service.PersonneService;

import com.example.soapservice.generated.AddPersonneRequest;
import com.example.soapservice.generated.AddPersonneResponse;
import com.example.soapservice.generated.GetPersonneRequest;
import com.example.soapservice.generated.GetPersonneResponse;
import com.example.soapservice.generated.GetAllPersonnesRequest;
import com.example.soapservice.generated.GetAllPersonnesResponse;
import com.example.soapservice.generated.UpdatePersonneRequest;
import com.example.soapservice.generated.UpdatePersonneResponse;
import com.example.soapservice.generated.DeletePersonneRequest;
import com.example.soapservice.generated.DeletePersonneResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PersonneEndpoint {

    private static final String NAMESPACE =
            "http://example.com/personnes";

    private final PersonneService service;

    public PersonneEndpoint(PersonneService service) {
        this.service = service;
    }

    private com.example.soapservice.generated.Personne convertir(
            com.example.soapservice.entity.Personne entity) {

        com.example.soapservice.generated.Personne personne =
                new com.example.soapservice.generated.Personne();

        personne.setId(entity.getId());
        personne.setNom(entity.getNom());
        personne.setPrenom(entity.getPrenom());
        personne.setAge(entity.getAge());

        return personne;
    }

    // CREATE
    @PayloadRoot(namespace = NAMESPACE, localPart = "addPersonneRequest")
    @ResponsePayload
    public AddPersonneResponse ajouter(
            @RequestPayload AddPersonneRequest request) {

        var entity = service.ajouter(
                request.getNom(),
                request.getPrenom(),
                request.getAge()
        );

        AddPersonneResponse response = new AddPersonneResponse();
        response.setPersonne(convertir(entity));

        return response;
    }

    // READ
    @PayloadRoot(namespace = NAMESPACE, localPart = "getPersonneRequest")
    @ResponsePayload
    public GetPersonneResponse rechercher(
            @RequestPayload GetPersonneRequest request) {

        GetPersonneResponse response = new GetPersonneResponse();

        service.rechercher(request.getId())
                .ifPresent(personne ->
                        response.setPersonne(convertir(personne)));

        return response;
    }

    // READ ALL
    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllPersonnesRequest")
    @ResponsePayload
    public GetAllPersonnesResponse lister(
            @RequestPayload GetAllPersonnesRequest request) {

        GetAllPersonnesResponse response =
                new GetAllPersonnesResponse();

        service.lister().forEach(personne ->
                response.getPersonne().add(convertir(personne)));

        return response;
    }

    // UPDATE
    @PayloadRoot(namespace = NAMESPACE, localPart = "updatePersonneRequest")
    @ResponsePayload
    public UpdatePersonneResponse modifier(
            @RequestPayload UpdatePersonneRequest request) {

        UpdatePersonneResponse response = new UpdatePersonneResponse();

        service.modifier(
                request.getId(),
                request.getNom(),
                request.getPrenom(),
                request.getAge()
        ).ifPresent(personne ->
                response.setPersonne(convertir(personne)));

        return response;
    }

    // DELETE
    @PayloadRoot(namespace = NAMESPACE, localPart = "deletePersonneRequest")
    @ResponsePayload
    public DeletePersonneResponse supprimer(
            @RequestPayload DeletePersonneRequest request) {

        DeletePersonneResponse response = new DeletePersonneResponse();

        response.setSuccess(service.supprimer(request.getId()));

        return response;
    }
}
