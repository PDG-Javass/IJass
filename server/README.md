# Serveur IJass

## Description

Ceci est le serveur (backend) de l'application de Jass automatisé **IJass** par _Javass_.

Fournissant l'API, il est implémenté à l'aide du framework [Spring](https://spring.io/) en Java.

## Installation

### Docker

Un fichier `Dockerfile` est fourni pour construire une image [Docker](https://www.docker.com/) du client.

### Dépendances

Les logiciels suivants sont nécessaires pour le développement du serveur:
- Java 17
- Maven
- Docker (pour le packaging du conteneur)

### Développement local

Pour exécuter le serveur localement (sur [http://localhost:8080/]())

Construire le serveur avec la commande suivante:

`mvn clean package`

Lancer le serveur avec la commande suivante:

`java -jar target/nom_archive.jar`

en remplaçant `nom_archive.jar` par le nom de l'archive .jar générée.
