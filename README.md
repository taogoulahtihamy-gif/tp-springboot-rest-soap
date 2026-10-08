# TP Spring Boot REST / SOAP - CRUD Personne

## Présentation

Ce projet consiste à développer une API REST Spring Boot
qui consomme un service SOAP pour gérer des personnes.

L'application REST joue le rôle de passerelle vers le service SOAP.

## Architecture

Client HTTP (JSON)
       |
       v
API REST - Port 8080
       |
       v
WebServiceTemplate
       |
       | SOAP / XML
       v
Service SOAP - Port 8081
       |
       v
Spring Data JPA
       |
       v
Base de données H2

## Technologies

- Java 17
- Spring Boot 3.5.7
- Maven
- Spring Web
- Spring Web Services
- SOAP / WSDL / XSD
- JAXB / JAX-WS
- Spring Data JPA
- Base H2
- API REST JSON

## Structure

- soap-service : fournisseur SOAP
- rest-client : API REST consommant SOAP

## Fonctionnalités

- Créer une personne
- Consulter une personne
- Lister les personnes
- Modifier une personne
- Supprimer une personne

## Démarrage

### 1. Démarrer SOAP

cd soap-service
mvn spring-boot:run

Service SOAP :
http://localhost:8081/ws

WSDL :
http://localhost:8081/ws/personnes.wsdl

Console H2 :
http://localhost:8081/h2-console

Configuration H2 :
JDBC URL : jdbc:h2:mem:personnesdb
Utilisateur : sa
Mot de passe : vide

### 2. Démarrer REST

Dans un autre terminal :

cd rest-client
mvn spring-boot:run

API :
http://localhost:8080/api/personnes

## Endpoints REST

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | /api/personnes | Lister |
| GET | /api/personnes/{id} | Consulter |
| POST | /api/personnes | Créer |
| PUT | /api/personnes/{id} | Modifier |
| DELETE | /api/personnes/{id} | Supprimer |

## Exemple de création

curl -X POST http://localhost:8080/api/personnes \
-H "Content-Type: application/json" \
-d '{"nom":"Mahamat","prenom":"Ali","age":25}'

## Exemple de consultation

curl http://localhost:8080/api/personnes

## Tests

Les cinq opérations CRUD ont été testées avec succès.

Codes HTTP vérifiés :

- GET : 200
- POST : 201
- PUT : 200
- DELETE : 204
- Ressource inexistante : 404

## Remarque

L'API REST ne possède aucune base de données.
Toutes les opérations passent par le service SOAP.

La base H2 fonctionne en mémoire.
Les données sont réinitialisées au redémarrage du service SOAP.
