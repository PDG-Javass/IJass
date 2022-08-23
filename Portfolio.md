# IJass

## Description du projet

Pour ce projet de groupe, nous avons décidé de créer une plateforme de jeu de Jass multijoueur. L'application permettra à l'utilisateur
de jouer au Jass contre un bot ou un autre joueur au travers d'une session. 

### Licence et code de conduite

Le projet est disponible sous [licence MIT](https://en.wikipedia.org/wiki/MIT_License).

Il est fortemenent recommandé de lire et d'appliquer le [code de conduite](./CODE_OF_CONDUCT.md).

### Auteurs
- Egger Magali
- Huart Hugo
- Nunez Tania
- Vogel Maelle
- Wichoud Nicolas

### Installation et utilisation

A suivre...

## Processus de developpement

### Méthodologie

Afin de mener à bien ce projet, nous avons décidé d'utiliser la méthode agile tout en y associant des principe de la programmation SCRUM.
Comme nous préférons avancer à petits pas rapides sur notre projet, nous avons pensé que cette méthode incrémentale conviendrait au groupe.

### Organisation du repository github

#### Branches

- *main* : dernière version stable du logiciel. Branche protégée et est modifiée uniquement par une PR faite depuis la branche
*develop*
- *develop* : version courante de l'application. C'est cette brancher qui sera clonée lors de la création d'une nouvelle feature.
- *feature/nom_feature* : ajout de fonctionnalités à la version courante
- *fix/nom_fix* : résolution d'issues

La branche *main* ne pourra être modifiée que sous certaines conditions.

### Technologies utilisées

#### Frontend

- Javascript

#### Backend

- Java
- Scala (à voir)
- Docker/Kubernetes (à définir, mais plutôt Docker)

### Build et deployement

Build sous environnement X (à définir) avec Y (compilateur à définir aussi).
Deployement via image docker ou Kubernetes (à choisir, mais préférence pour docker).

### Ajout d'une fonctionnalité

Afin d'ajouter une fonctionnalité au projet, il faut commencer par cérer une nouvelle branche dûment nommée (p.ex feat/nomfeature). Tout ajout de code concernant la fonctionnalité se fait sur la branche créée, puis pull request lors de la fin de la feature. La requête est ensuite reviewed
par un autre membre du groupe puis merge ou non selon l'état de la branche feature.

### Tests

Conception des tests à la main et automatisation via un outil (à définir). Nous prévoyons de nous concentrer sur des tests unitaires, puis d'éventuellement pousser les tests plus loins si nous en avons le temps et les moyens.

## Convention de nommage et stories

### User stories

Créer une issue sur github afin de définir les actions git autorisées par les différents acteurs. Définir le temps optimiste, réaliste et
pessimiste que prendra la story. Attitrer la story. Créer une nouvelle branche, nommée *feat/nom_feature* depuis le main et travailler uniquement
sur cette branche. Commit early, commit often. Une fois la story terminée, effectuer une pull request afin qu'elle soit revue par un autre
membre du groupe. Si la story est validée, effectuer un merge de la branche sur le main, sinon le responsable de la branche doit régler les issues
créées par la story.

### Issues

Créer une issue sur github et la relier à la story qui la concerne. Effectuer une estimation de temps (avant ou après avoir travaillé dessus). Effectuer
un feedback sur le temps de résolution et l'estimation. Les personnes s'auto-attitrent les issues et leur validation doit être faite par un membre
tiers.

### Commits
Lorsqu'un commit est effectué, il faut impérativement que son message permette de saisir sa nature à l'aide d'une description succinte.
Le modèle de message de commit est donc le suivant:
`<type> : <description>

<corps du message optionnel>`
Les types de commits peuvent être les suivants:
- feat: ajout d'une fonctionnalité au code
- fix: correction d'un bug
- docs: ajout ou modification de la documentation
- style: changement de la présentation du code (identation, fin de ligne, ...)
- perf: amélioration de la performance du code
- chore: modification n'impactant pas l'utilisateur (p.ex modifications du fichier pom.xml)

## Tableau Kanban

Nous avons décidé d'utiliser un tableau Kanban afin de travailler avec no user stories :
- Product Backlog
- A faire
- En cours
- Terminé

# Sprint 1


## Fonctionnalités espérées

Voici les fonctionnalités minimum qui seront ajoutées au projet durant ces trois semaines.

- Les 36 cartes sont mélangées.
- Les 36 cartes sont distribuées aux 4 joueurs (donc 9 par personne).
- Chaque joueur pose une carte par tour.
- La carte la plus forte emporte la plie.
- Si notre coéquipier gagne la plie, le score s'incrémente.
- Le score s'incrémente du nombre de points selon ce qui est écrit dans livrable/Semaine1.md.
- Le joueur choisit l'atout.
- L'atout l'emporte sur les trois autres familles.
- Le buur puis le nell sont les deux plus fortes cartes.
- Le joueur est obligé de suivre la famille qui a été posé en premier.
    - Exception si le joueur n'a pas la famille qui a été posé. Il peut poser n'importe quelle autre carte.
    - Le joueur peut couper avec un atout si une autre famille a été posé. 
