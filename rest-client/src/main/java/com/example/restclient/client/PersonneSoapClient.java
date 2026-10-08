package com.example.restclient.client;

import com.example.restclient.generated.*;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
public class PersonneSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    public PersonneSoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    // CREATE
    public AddPersonneResponse ajouter(
            String nom, String prenom, int age) {

        AddPersonneRequest request = new AddPersonneRequest();

        request.setNom(nom);
        request.setPrenom(prenom);
        request.setAge(age);

        return (AddPersonneResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }

    // READ
    public GetPersonneResponse rechercher(Long id) {

        GetPersonneRequest request = new GetPersonneRequest();

        request.setId(id);

        return (GetPersonneResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }

    // READ ALL
    public GetAllPersonnesResponse lister() {

        GetAllPersonnesRequest request =
                new GetAllPersonnesRequest();

        return (GetAllPersonnesResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }

    // UPDATE
    public UpdatePersonneResponse modifier(
            Long id, String nom, String prenom, int age) {

        UpdatePersonneRequest request =
                new UpdatePersonneRequest();

        request.setId(id);
        request.setNom(nom);
        request.setPrenom(prenom);
        request.setAge(age);

        return (UpdatePersonneResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }

    // DELETE
    public DeletePersonneResponse supprimer(Long id) {

        DeletePersonneRequest request =
                new DeletePersonneRequest();

        request.setId(id);

        return (DeletePersonneResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }
}
