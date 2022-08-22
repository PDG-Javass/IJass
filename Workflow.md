# IJass

## Description du projet

Pour ce projet de groupe, nous avons décidé de créer une plateforme de jeu de Jass multijoueur. L'application permettra à l'utilisateur
de jouer au Jass contre un bot ou un autre joueur au travers d'une session. 

### Auteurs
- Egger Magali
- Huart Hugo
- Nunez Tania
- Vogel Maelle
- Wichoud Nicolas

### Installation et utilisation

A suivre...

# Worklof IJass

## Processus de developpement

### Organisation du repository github

#### Branches

- *main* : dernière version stable du logiciel. Branche protégée et est modifiée uniquement par une PR faite depuis la branche
*develop*
- *develop* : version courante de l'application. C'est cette brancher qui sera clonée lors de la création d'une nouvelle feature.
- *feature/nom_feature* : ajout de fonctionnalités à la version courante
- *fix/nom_fix* : résolution d'issues

### Technologies utilisées

#### Frontend

- Javascript

#### Backend

- Java
- Scala (à voir)

### Build et deployement

Build sous environnement X (à définir) avec Y (compilateur à définir aussi).
Deployement via image docker ou Kubernetes (à choisir, mais préférence pour docker).

### Ajout d'une fonctionnalité

Création d'une nouvelle branche dûment nommée (p.ex feat/nomfeature). Conception locale, puis pull request lors de la fin de la feature. La requête est ensuite reviewed
par un autre membre du groupe puis merge ou non selon l'état de la branche feature.

### Tests

Conception des tests à la main et automatisation via un outil (à choisir).

## Convention de nommage et stories

### User stories

Créer une issue sur github afin de définir les actions git autorisée par les différents acteurs. Définir le temps optimiste, réaliste et
pessimiste que prendra la story. Attitrer la story. Créer une nouvelle branche, nommée *feat/nom_feature* depuis le main et travailler uniquement
sur cette branche. Commit early, commit often. Une fois la story terminée, effectuer une pull request afin qu'elle soit revue par un autre
membre du groupe. Si la story est validée, effectuer un merge de la branche sur le main, sinon le responsable de la branche doit régler les issues
créées par la story.

## Issues

Créer une issue sur github et la relier à la story qui la concerne. Effectuer une estimation de temps (avant ou après avoir travaillé dessus). Effectuer
un feedback sur le temps de résolution et l'estimation. Les personnes s'auto-attitrent les issues et leur validation doit être faite par un membre
tiers.

## Tableau Kanban

Nous avons décidé d'utiliser un tableau Kanban afin de travailler avec no user stories :
- Product Backlog
- A faire
- En cours
- Terminé


