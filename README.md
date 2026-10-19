# Gestion des filieres et des etudiants

Projet Spring Boot compose de deux microservices independants :

- `filieres-service` : CRUD des filieres sur le port `8081`
- `etudiants-service` : CRUD des etudiants sur le port `8082`

Le service etudiants stocke seulement `filiereId`. Lorsqu'un etudiant est recupere, il appelle le service filieres avec `RestClient` pour inclure les informations completes de la filiere dans la reponse.

## Lancer les services

Dans deux terminaux differents :

```bash
./mvnw -pl filieres-service spring-boot:run
```

```bash
./mvnw -pl etudiants-service spring-boot:run
```

Le service filieres doit etre lance avant de lire ou creer des etudiants, car `etudiants-service` verifie et enrichit les donnees via `http://localhost:8081`.

## Documentation Swagger / OpenAPI

Chaque service expose une documentation generee avec Springdoc :

- Filieres Swagger UI : `http://localhost:8081/swagger-ui.html`
- Filieres OpenAPI JSON : `http://localhost:8081/v3/api-docs`
- Etudiants Swagger UI : `http://localhost:8082/swagger-ui.html`
- Etudiants OpenAPI JSON : `http://localhost:8082/v3/api-docs`

## Endpoints filieres

- `GET http://localhost:8081/api/filieres`
- `GET http://localhost:8081/api/filieres/{id}`
- `POST http://localhost:8081/api/filieres`
- `PUT http://localhost:8081/api/filieres/{id}`
- `DELETE http://localhost:8081/api/filieres/{id}`

Exemple :

```bash
curl -X POST http://localhost:8081/api/filieres \
  -H "Content-Type: application/json" \
  -d '{"code":"TM","libelle":"Techniques de Management","description":"Management et organisation"}'
```

## Endpoints etudiants

- `GET http://localhost:8082/api/etudiants`
- `GET http://localhost:8082/api/etudiants/{id}`
- `POST http://localhost:8082/api/etudiants`
- `PUT http://localhost:8082/api/etudiants/{id}`
- `DELETE http://localhost:8082/api/etudiants/{id}`

Exemple :

```bash
curl -X POST http://localhost:8082/api/etudiants \
  -H "Content-Type: application/json" \
  -d '{"nom":"Haddad","prenom":"Imane","email":"imane.haddad@example.com","filiereId":1}'
```

Exemple de reponse enrichie :

```json
{
  "id": 1,
  "nom": "Alaoui",
  "prenom": "Sara",
  "email": "sara.alaoui@example.com",
  "filiereId": 1,
  "filiere": {
    "id": 1,
    "code": "GI",
    "libelle": "Genie Informatique",
    "description": "Developpement logiciel et systemes d'information"
  }
}
```

## Bases de donnees H2

- Filieres : `http://localhost:8081/h2-console`, JDBC URL `jdbc:h2:mem:filieres-db`
- Etudiants : `http://localhost:8082/h2-console`, JDBC URL `jdbc:h2:mem:etudiants-db`

Utilisateur : `sa`, mot de passe vide.
