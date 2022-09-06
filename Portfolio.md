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

- Svelte (Javascript FW)
- Hébergement sur Netlify

#### Backend

- Java
- Spring
- Docker
- Google Cloud Run

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
- User Stories
- A faire
- En cours
- Terminé

# Sprint 1

## Fonctionnalités espérées

Voici les fonctionnalités minimum qui seront ajoutées au projet durant ces trois semaines.

- Les 36 cartes sont mélangées.
- Les 36 cartes sont distribuées aux 4 joueurs (donc 9 par personne).
- Chaque joueur pose une carte par plie.
- La carte la plus forte emporte la plie.
- Si notre coéquipier ou nous-même gagnons la plie, le score s'incrémente.
- Le score s'incrémente du nombre de points selon ce qui est écrit au-dessus.
- Le joueur choisit l'atout.
- L'atout l'emporte sur les trois autres familles.
- Le buur puis le nell sont les deux plus fortes cartes.
- Le joueur est obligé de suivre la famille qui a été posé en premier.
  - Exception si le joueur n'a pas la famille qui a été posé. Il peut poser n'importe quelle autre carte.
  - Le joueur peut couper avec un atout si une autre famille a été posée.
  - La sous-coupe est interdite. Si quelqu'un coupe on ne peut pas mettre un atout plus faible. Sauf si c'est notre dernière carte.
- Notre coéquipier est toujours lapinou car il est en face de nous.
- Le bot respecte les règles, mais joue une carte aléatoire.

# Tests du respect des règles

| Règle à tester | Réussite du test |
|:-|:-:|
|Au début de chaque manche, les 36 cartes sont mélangées de façon aléatoire | - |
|Les joueurs commencent chaque manche avec 9 cartes aléatoires chacun|-|
|Les cartes dans la main du joueur actif sont triées en tout temps par couleur dans l'ordre carreau-pique-coeur-trèfle dans le sens de lecture|-|
|Les cartes dans la main du joueur actif de même couleur sont ensuite triées entre elles par puissance croissante dans le sens de lecture sans prendre en compte le buur et le nell|-|
|Chaque carte est présente une et une seule fois dans l'ensemble des mains de tous les joueurs au début de chaque manche|-|
|Chaque joueur peut et doit poser une et une seule carte jouable par tour avant de passer au joueur suivant|-|
|Chacune des deux équipes et constituée de deux joueurs situés de part et d'autre du tapis de jeu|-|
|Le joueur qui choisit l'atout pour la première manche est celui qui possède le 7 de carreau|-|
|Les joueurs choisissent l'atout chacun leur tour dans le sens inverse des aiguilles d'une montre|-|
|Le joueur qui choisit l'atout à chaque manche joue la première carte de la première plie de la manche|-|
|Les joueurs posent une carte chacun leur tour dans le sens inverse des aiguilles d'une montre|-|
|Le joueur qui remporte une plie pose la première carte de la plie suivante|-|
|Si la première carte de la plie est un atout, tous les joueurs qui possèdent au moins un atout différent du buur sont obligés de poser un atout|-|
|Si la première carte de la plie est un atout, le joueur actif peut poser n'importe quelle carte s'il ne possède pas d'atouts ou uniquement le buur|-|
|Si la première carte de la plie n'est pas un atout, tous les joueurs qui peuvent suivre dans cette couleur doivent le faire ou couper la plie|-|
|Si la première carte de la plie n'est pas un atout, le joueur actif peut poser n'importe quelle carte s'il ne peut pas suivre dans la couleur d'entrée|-|
|Si la première carte de la plie n'est pas un atout et qu'un joueur coupe la plie, les joueurs suivants ne peuvent pas sous-couper à moins de ne pas avoir le choix|-|
|L'équipe qui remporte la dernière plie de la manche remporte 5 points supplémentaires|-|
|A la fin de chaque manche, 157 points sont distribués entre les deux équipes|-|
|Quand une équipe atteint 1000 points, la partie s'arrête et l'équipe qui a atteint ce pallier est déclarée vainqueure|-|
|Si aucun atout n'est posé, le joueur qui pose la carte la plus haute de la même couleur que la carte d'entrée remporte la plie|-|
|Si un ou plusieurs joueurs coupent la plie, le joueur ayant posé l'atout le plus puissant remporte la plie|-|
|En dehors de l'atout, l'ordre de puissance des cartes est le suivant, de la plus forte à la plus faible : as, roi, dame, valet, dix, neuf, huit, sept et six|-|
|En dehors de l'atout, l'as vaut 11 points, le roi 4 points, la dame 3 points, le valet 2 points, le dix 10 points et le reste des cartes 0 point|-|
|En atout, l'ordre de puissance des cartes est le suivant, de la plus forte à la plus faible : valet/buur, neuf/nell, as, roi, dame, dix, huit, sept et six|-|
|En atout, le valet/buur vaut 20 points, le neuf/nell 14 points, l'as 11 points, le roi 4 points, la dame 3 points, le dix 10 points et le reste des cartes 0 point|-|

# Sprint 2

## Implémentation du jeu

Nous avons créé une classe *GameManager* qui permet de gérer une partie de Jass.
Fonctionnalités implémentées:
- Création du GameManager ainsi que des 4 joueurs dont 3 bots.
- Initialisation d'un round
- Jouer un round
- Joueur une plie
- Calculer les scores à la fin de chaque plie
- Simulation d'une partie entre 4 bots

## Tests effectués

- Distribution aléatoire des cartes
- Fin de plie lorsque les 4 joueurs ont joué
- Fin de round lorsque les 36 cartes ont été jouées
- Mise à jour du joueur commençant le round
  - Premier round : le joueur possédant le 7 de carreau
  - Autres rounds : le prochain joueur dans l'ordre de jeu

Nous avons une application, sans interaction externe, qui fonctionne correctement (état des plies, gagnant de plie, équipe gagnante, calcul des points). Cette simulation consiste en 4 bots qui jouent entre eux pour l'instant.

## JSON de l'état partagé

Voici la représentation JSON générée par le serveur pour réprésenter l'état de la partie.



```json
{
  "game":{
      "id":0,
      "round":{
         "id":0,
         "trump":0,
         "beginner":0,
         "cards" : [
            {
                  "family":0,
                  "value":0
            },
            {
                  "family":0,
                  "value":1
            },
            {
                  "family":0,
                  "value":2
            },
            {
                  "family":0,
                  "value":3
            },
            {
                  "family":0,
                  "value":4
            },
            {
                  "family":0,
                  "value":5
            },
            {
                  "family":0,
                  "value":6
            },
            {
                  "family":0,
                  "value":7
            },
            {
                  "family":0,
                  "value":8
            },
         ],
         "fold":{
            "id":0,
            "winner":0,
            "score_ally":0,
            "score_ennemy":0,
            "played":[
               {
                  "name":"bot1",
                  "card":{
                     "family":0,
                     "value":0
                  }
               },
               {
                  "name":"bot2",
                  "card":{
                     "family":0,
                     "value":0
                  }
               },
               {
             Joueur        "family":0,
                     "value":0
                  }
               }
            ]
         }
      }
   }
}
```

## Numérotation

### Joueurs

- 0: joueur réel
- 1: bot opposant
- 2: bot allié
- 3: bot opposant

### Famille

- 0: pique
- 1: tréfle
- 2: coeur
- 3: carreaux

## Problèmes rencontrés

La mise en place du jeu a s'est bien passé, nous avons néanmoins rencontré les difficultés suivantes:
- Gestion de l'atout lors des calcul du gagnant de la plie
- Gestion du tour de jeu en fonction de l'état de la partie
- Utilisation de Svelte pour le front :
  - <A compléter par Hugo et Maelle>

# Sprint 3

## Requêtes et ccommunication entre le client et le serveur

Lors de ce sprint nous nous sommes intéressés à la connexion entre le client et le serveur.
Comme décris dans le précédent sprint, nous avons choisi de communiquer l'état de la partie en JSON.

Voici le diagramme de séquence de la communication entre nos deux entités:
[DiagrammeSéquence](/livrables/Img/DiagrammeSequence.png)

## Problèmes rencontrés

Ayant travaillé principalement en deux groupes: le backend et le frontend, nous avons rencontrés les problèmes suivant lors de leur connexion:
- L'adaptation de la simulation (les bots jouent entre eux) en jeu interactif a été plus fastidieux que prévu car:
  -   Le jeu ayant entiérement implémenté dans le GameManager, il a dû être segmenté afin d'être manipulé de façon plus atomique via les requêtes faites au serveur
  -   Les changements effectués au point précédent ont causé 'apparition de certaines erreurs dans notre application ajoutant un travail de debug supplémentaire
- Nous n'avions pas été assez dans les détails lorsque nous avons imaginé la première version de l'application, cela demandant des changements intermédiaires :
  - La communication n'ayant pas été totalement défini tôt dans le projet, il a fallu adapter le code des deux côtés
  - Nous avons créé le jeu (application serveur) sans considérer les informations que nécessite le GameManager et à quels moments, il a donc fallu fragmenter l'exécution initiale du jeu afin de l'adapter au mapping client-serveur
