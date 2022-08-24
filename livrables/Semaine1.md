# PDG 2022 - Livrable Semaine 1

## Description du projet

Pour ce travail de groupe, nous avons décidé de concevoir un jeu de Jass. L'objectif de ce projet est de pouvoir
jouer au Jass avec un/des joueurs/bots en ligne. Un utilisateur peut jouer une partie en faisant équipe avec un
autre joueur ou un bot.
Cette plateforme de jeu permettra aux utilisateurs de jouer au Jass seul ou avec ses amis tout en comptabilisant
les points au fil des parties.
Lorsqu'un utilisateur arrive sur la plateforme, il peut choisir de jouer en local (avec et contre des bots) ou
en ligne (avec et/ou contre des bots/joueurs).
Lorsqu'il joue en ligne, un joueur peut créer une partie ou la rejoindre. Le propriétaire d'une partie peut ajouter
ou retirer des bots comme il le souhaite pour atteindre 4 joueurs.

## Requirements fonctionnels

Notre programme doit permettre de se connecter et jouer une partie à tout moment. Dès lors, nous aurons besoin de
deux entités:
- Client : permettant aux utilisateurs de se connecter à la plateforme.
- Serveur : permet aux utilisateurs de jouer entre eux ou contre la machine.
  Le serveur sera un simple container Docker *définir quelle image on choisit !* sur lequel notre script Java sera
  éxécuté.

Les joueurs jouent par équipe de deux et ne peuvent pas jouer l'un après l'autre.
La Hiérarchie des cartes est la suivante: Toutes les couleurs sauf l'atout suit les règles conventionnelles
(As, Roi, Reine, Valet, 10, 9, ...). Concernant l'atout, la carte la plus forte est le valet, que l'on nomme
*buur* suivi par le neuf, appelé *nell*. Les autres cartes suivent les règles conventionnelles entre elles.
L'atout bat toutefois les cartes d'autres couleurs quelles qu'elles soient.


Chaque partie comprend plusieurs round, eux-même composés de 9 tours.
Au début de chaque round, chaque joueur se voit distribuer 9 cartes parmis un paquet de 36 cartes. Un des joueur choisit
la couleur de l'atout.
Une fois la partie commencée, chaque joueur pose une carte à chaque tour. La première carte posée définit la couleur
courante du tour. Un joueur se voit obliger de *jeter* une de ses cartes si il ne possède pas la couleur
couramment jouée ou de l'atout. À contrario, si un joueur possède une unique carte de la couleur jouée, il se voit
obligé de la jouer, sauf s'il s'agit du buur.


Un tour se termine lorsque chaque joueur a joué ou jeté une carte. Au terme de chaque tour, la personne ayant posé
la carte la plus forte remporte le tas pour son équipe.
Un round se termine lorsque toutes les cartes ont été jouées. Les points sont alors compatibilisés selon les règles
suivantes:
- Buur 20 pts
- Nell 14 pts
- As 11 pts
- Dix 10 pts
- Roi 4 pts
- Reine 3 pts
- Valet 2 pts

Toutes les autres cartes valent 0 pts.
La victoire est remportée par la première équipe atteignant les 1000 pts.

Chaque partie doit se terminer par un arrêt volontaire, une victoire ou une défaite de l'utilisateur.

### Client

Concernant le client de notre programme, il nous faudra:
- Game Manager : qui interagit avec l'utilisateur et transmet les commandes au jeu
- Modélisation des cartes sous la forme d'une classe
- Assets pour les cartes à jouer
- UI permettant d'afficher la partie en cours


## Requirements non-fonctionnels

Le programme doit assurer une partie fluide, en tout cas pendant chaque tour. Ce qui implique que l'interaction
entre l'utilisateur et le serveur doit être rapide et que le bot doit jouer rapidement.
L'affichage des points doit être propre et soit affiché en permanence, soit accessible facilement.
Lors de session de jeu multijoueur, la connexion entre eux doit rester stable et si l'un des joueurs se voit
déconnecté, il doit pouvoir rejoindre la partie ou être remplacé par un bot.

## Choix technologiques

### Application
Frontend
- Javascript

Backend
- Java
- Scala (à voir)
- Docker/Kubernetes (à définir, mais plutôt Docker)

### Outils utilisés sur le repository github
Nous avons décidé d'utiliser gitflow afin de travailler propremement sur différentes branches avec différents
niveaux de protection.
Nous avons configuré la branche main afin qu'elle ne puisse pas être modifiée par une autre action
qu'une Pull Request. Cette même PR doit être validée par une autre personne.
Le workflow gitflow consiste également en la création de la branche *develop* qui est celle sur laquelle
nous allons effectivement travailler. Lorsque nous souhaitons effectuer une release, il suffit de merge
la branche develop au *main*. Chaque fonctionnalité nécessite donc la création d'une nouvelle branche à partir
de *develop* et tout ajout de code se fait exclusivement sur les branches prévues à cet effet. Une fois la feature
terminée, une PR permet de la merge à la branche *develop*.

## Mockups

## Landing page

## Méthodologie
