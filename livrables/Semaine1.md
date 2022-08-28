# PDG 2022 - Livrable Semaine 1

Auteurs : Egger Magali, Huart Hugo, Nunez Tania, Vogel Maëlle, Wichoud Nicolas

## Description du projet

Pour ce travail de groupe, nous avons décidé de concevoir un jeu de Jass. L'objectif de ce projet est de pouvoir
jouer au Jass avec un/des joueurs/bots en ligne. Un utilisateur peut jouer une partie en faisant équipe avec un
autre joueur ou un bot.
Cette plateforme de jeu permettra aux utilisateurs de jouer au Jass seul ou avec ses amis tout en comptabilisant
les points au fil des manches et des parties.
Lorsqu'un utilisateur arrive sur la plateforme, il peut choisir de jouer en local (avec et contre des bots) ou
en ligne.
Lorsqu'il joue en ligne, un joueur peut créer une partie ou la rejoindre. Le propriétaire d'une partie peut ajouter
ou retirer des bots comme il le souhaite pour atteindre 4 joueurs.

## Requirements fonctionnels

Notre programme doit permettre de se connecter et jouer une partie à tout moment. Dès lors, nous aurons besoin de
deux entités principales :
- Le client : permettant aux utilisateurs de se connecter à la plateforme et gérant les interactions entre le joueur
  et le jeu.
- Le serveur : permet aux utilisateurs de jouer entre eux ou contre la machine.
  Le serveur est un simple container Docker (l'image choisie est alpine 17) déployé sur la plateforme Google Cloud
  Run.

Lorsqu'un joueur lance une partie seul, il se voit attribuer un id unique de partie qui sera utilisé dans les requêtes
GET. Dès lors plusieurs parties peuvent avoir lieu en même temps.

Les joueurs jouent par équipe de deux et jouent en alternance.

La Hiérarchie des cartes est la suivante :

Toutes les couleurs sauf l'atout suivent les règles conventionnelles
(As, Roi, Reine, Valet, 10, 9, ..., 6). Concernant l'atout, la carte la plus forte est le valet, que l'on nomme
*buur* suivi par le neuf, appelé *nell*. Les autres cartes suivent les règles conventionnelles entre elles.
L'atout bat toutefois les cartes d'autres couleurs quelles qu'elles soient.


Chaque partie comprend plusieurs rounds, eux-mêmes composés de 9 tours.
Au début de chaque round, chaque joueur se voit distribuer 9 cartes parmi un paquet de 36 cartes. Un des joueurs choisit
la couleur de l'atout.

Une fois la partie commencée, chaque joueur pose une carte par tour. La première carte posée définit la couleur
courante du tour. Un joueur se voit obliger de *jeter* une de ses cartes s'il ne possède pas la couleur
couramment jouée ou de l'atout pour *couper* la plie. À contrario, si un joueur possède une unique carte de la couleur
jouée, il se voit obligé de la jouer, sauf s'il s'agit du buur.


Un tour se termine lorsque chaque joueur a joué ou jeté une carte. Au terme de chaque tour, la personne ayant posé
la carte la plus forte remporte le tas pour son équipe.
Un round se termine lorsque toutes les cartes ont été jouées. Les points sont alors comptabilisés selon les règles
suivantes :
- Buur 20 pts
- Nell 14 pts
- As 11 pts
- Dix 10 pts
- Roi 4 pts
- Reine 3 pts
- Valet 2 pts

Toutes les autres cartes valent 0 pt.
La victoire est remportée par la première équipe atteignant les 1000 pts.

Chaque partie doit se terminer par un arrêt volontaire, une victoire ou une défaite de l'utilisateur.

Quand l'utilisateur se connecte au site, une page d'accueil s'affiche avec un unique bouton "Start a game". Quand il appuie sur ce bouton, le site lui propose alors de soit cliquer sur un lien qui redirige l'utilisateur vers un site expliquant les règles du chibre, soit d'appuyer sur un bouton "Continuer". Ce dernier permet d'accéder à un nouvel écran sur lequel l'utilisateur peut choisir entre deux boutons, respectivement lancer une "partie locale" ou une "partie entre amis".Une fois la partie lancée, il est possible de jouer une partie de jass. Lorsque c'est le tour de l'utilisateur de choisir l'atout, quatre boutons apparaissent représentant les quatres couleurs des cartes. La couleur de l'atout choisi sera affichée pendant la partie. Les points sont ajoutés au score de l'équipe qui a remporté la plie à la fin de celle-ci. Le score courant est visible en permanence dans un encart situé en haut à gauche de l'écran de jeu. Les cartes jouées sont positionnées devant le joueur auquel elles appartiennent. Un bouton "quitter le jeu" est présent en tout temps en haut à droite de l'écran de jeu et permet d'abandonner la partie et revenir à la page d'accueil. A la fin de chaque manche, un tableau récapitulatif des scores est affiché avant de passer à la manche suivante. A l'issue de la partie, un écran indiquant les scores finaux ainsi que la vitoire ou la défaite de l'utilisateur apparaît. L'utilisateur peut alors cliquer n'importe où sur l'écran pour revenir à l'écran d'accueil et relancer une partie ou quitter le jeu.

Un exemple détaillé de l'utilisation du site est présenté dans les mockups situés ci-dessous, indiquant ainsi visuellement les principaux requirements du site du point de vue d'un utilisateur lambda.

### Client

Concernant le client de notre programme, il nous faudra :
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
- Svelte (Javascript FW)
- Hébergement sur Netlify

Backend
- Java
- Spring
- Docker
- Google Cloud Run

### Déploiement

#### Frontend

Le frontend est déployé sur Netlify, accessible actuellement via l'URL de [déploiement par défaut](https://radiant-cucurucho-67d710.netlify.app/).

Une action GitHub est configurée pour effectuer le déploiement sur Netlify automatiquement (génération de l'application avec npm puis chargement sur Netlify), depuis la branche `main` à l'issue de chaque push sur celle-ci.

#### Backend

Le backend est déployé sur Google Cloud Run, accessible actuellement via l'URL de [déploiement par défaut](https://server-xxcwjwt7qq-ue.a.run.app/).

Comme pour le frontend, une action GitHub est configurée pour effectuer le déploiement sur Google Cloud Run (packaging avec Maven, création et chargement de l'image Docker dans Google Container Registry), lors de chaque modification de la branche `main`.

### Outils utilisés sur le repository github

Nous avons décidé d'utiliser gitflow afin de travailler proprement sur différentes branches avec différents
niveaux de protection.
Nous avons configuré la branche main afin qu'elle ne puisse pas être modifiée par une autre action
qu'un Pull Request. Cette même PR doit être validée par une autre personne.
La branche *develop* est définie comme celle par défaut sur notre repository.
Le workflow gitflow consiste également en la création de la branche *develop* qui est celle sur laquelle
nous allons effectivement travailler. Lorsque nous souhaitons effectuer une release, il suffit de merge
la branche develop au *main*. Chaque fonctionnalité nécessite donc la création d'une nouvelle branche à partir
de *develop* et tout ajout de code se fait exclusivement sur les branches prévues à cet effet. Une fois la feature
terminée, une PR permet de la merge à la branche *develop*.

## Mockups

La page d'accueil de Javass est la suivante :
![](Img/page_accueil.jpg)

Lorsque vous appuyez sur "Start a Game", vous êtes menés à une page vous proposant de relire les règles
si vous êtes un profane ou que vous n'avez pas joué depuis longtemps.

![](Img/page_regles.jpg)

Une fois votre relecture des règles terminée, appuyer sur continuer affichera une page permettant de choisir
le type de partie souhaitée.

![](Img/page_choix_type_partie.jpg)

Avant de commencer à jouer chaque manche, chacun leur tour, les joueurs choisissent la couleur d'atout.

![](Img/page_choix_atout.jpg)

Une fois la partie lancée, les joueurs et votre main sont affichés. Les cartes jouées sont face contre ciel
au centre de l'écran et le score en haut à gauche.

![](Img/page_partie_en_cours.jpg)

A la fin de chaque plie, le score de l'équipe qui a remporté la plie est automatiquement mis à jour en y ajoutant les points de celle-ci

![](Img/page_partie_en_cours_2.jpg)

A la fin de chaque manche, un tableau récapitulatif des scores de la manche est affiché avant de passer à la manche suivante.

![](Img/tableau_score.jpg)

À l'issue de la partie les scores sont affichés et les vainqueurs désignés.

![](Img/page_victoire.jpg)

![](Img/page_defaite.jpg)

## Landing page

*Fichier joint*

## Fonctionnalités espérées

Voici les fonctionnalités minimum qui seront ajoutées au projet durant ces trois semaines.

- Les 36 cartes sont mélangées.
- Les 36 cartes sont distribuées aux 4 joueurs (donc 9 par personne).
- Chaque joueur pose une carte par tour.
- La carte la plus forte emporte la plie.
- Si notre coéquipier gagne la plie, le score s'incrémente.
- Le score s'incrémente du nombre de points selon ce qui est écrit au-dessus.
- Le joueur choisit l'atout.
- L'atout l'emporte sur les trois autres familles.
- Le buur puis le nell sont les deux plus fortes cartes.
- Le joueur est obligé de suivre la famille qui a été posé en premier.
  - Exception si le joueur n'a pas la famille qui a été posé. Il peut poser n'importe quelle autre carte.
  - Le joueur peut couper avec un atout si une autre famille a été posée.
  - La sous-coupe est interdite. Si quelqu'un coupe on ne peut pas mettre un atout plus faible. Sauf si c'est notre dernière carte.

### Si le temps le permet

- Jouer en ligne avec des joueurs.
- Ajouter le chibre (demander à son coéquipier de choisir l'atout pour nous).
- Ajouter les annonces. Au début, on annonce des combinaisons spéciales de cartes pour gagner plus de points.
- Rendre les bots plus intelligent.

## Méthodologie

Afin de mener à bien ce projet, notre équipe a choisi de pratiquer une méthode agile SCRUM. Cette méthodologie
incrémentale correspond à notre dynamique de groupe. Nous allons donc effectuer ce travail sur 3 Sprints correspondant
à chaque semaine.

Chaque début de semaine, nous décomposerons le travail à faire en User stories, elles-mêmes possiblement
décomposées afin d'être partagées entre plusieurs membres du groupe. Chaque membre se voit attitrer (par un autre membre
ou lui-même) une story. Chaque membre doit effectuer trois estimations du temps que prendra la story.
Nous tenons toutefois à appliquer un peu le principe de pair programming si nécessaire.

À la fin d'un sprint, nous repasserons sur les estimations de temps et effectuerons un feedback général afin de
pouvoir améliorer ces aspects au fil des sprints. Si certaines stories ne sont pas achevée à terme du sprint
correspondant, il sera passé au prochain sprint avec la plus haute priorité.
# PDG 2022 - Livrable Semaine 1

Auteurs : Egger Magali, Huart Hugo, Nunez Tania, Vogel Maëlle, Wichoud Nicolas

## Description du projet

Pour ce travail de groupe, nous avons décidé de concevoir un jeu de Jass. L'objectif de ce projet est de pouvoir
jouer au Jass avec un/des joueurs/bots en ligne. Un utilisateur peut jouer une partie en faisant équipe avec un
autre joueur ou un bot.
Cette plateforme de jeu permettra aux utilisateurs de jouer au Jass seul ou avec ses amis tout en comptabilisant
les points au fil des manches et des parties.
Lorsqu'un utilisateur arrive sur la plateforme, il peut choisir de jouer en local (avec et contre des bots) ou
en ligne.
Lorsqu'il joue en ligne, un joueur peut créer une partie ou la rejoindre. Le propriétaire d'une partie peut ajouter
ou retirer des bots comme il le souhaite pour atteindre 4 joueurs.

## Requirements fonctionnels

Notre programme doit permettre de se connecter et jouer une partie à tout moment. Dès lors, nous aurons besoin de
deux entités principales :
- Le client : permettant aux utilisateurs de se connecter à la plateforme et gérant les interactions entre le joueur
  et le jeu.
- Le serveur : permet aux utilisateurs de jouer entre eux ou contre la machine.
  Le serveur est un simple container Docker (l'image choisie est alpine 17) déployé sur la plateforme Google Cloud
  Run.

Lorsqu'un joueur lance une partie seul, il se voit attribuer un id unique de partie qui sera utilisé dans les requêtes
GET. Dès lors plusieurs parties peuvent avoir lieu en même temps.

Les joueurs jouent par équipe de deux et jouent en alternance.

La Hiérarchie des cartes est la suivante :

Toutes les couleurs sauf l'atout suivent les règles conventionnelles
(As, Roi, Reine, Valet, 10, 9, ..., 6). Concernant l'atout, la carte la plus forte est le valet, que l'on nomme
*buur* suivi par le neuf, appelé *nell*. Les autres cartes suivent les règles conventionnelles entre elles.
L'atout bat toutefois les cartes d'autres couleurs quelles qu'elles soient.


Chaque partie comprend plusieurs rounds, eux-mêmes composés de 9 tours.
Au début de chaque round, chaque joueur se voit distribuer 9 cartes parmi un paquet de 36 cartes. Un des joueurs choisit
la couleur de l'atout.
Une fois la partie commencée, chaque joueur pose une carte par tour. La première carte posée définit la couleur
courante du tour. Un joueur se voit obliger de *jeter* une de ses cartes s'il ne possède pas la couleur
couramment jouée ou de l'atout pour *couper* la plie. À contrario, si un joueur possède une unique carte de la couleur
jouée, il se voit obligé de la jouer, sauf s'il s'agit du buur.


Un tour se termine lorsque chaque joueur a joué ou jeté une carte. Au terme de chaque tour, la personne ayant posé
la carte la plus forte remporte le tas pour son équipe.
Un round se termine lorsque toutes les cartes ont été jouées. Les points sont alors comptabilisés selon les règles
suivantes :
- Buur 20 pts
- Nell 14 pts
- As 11 pts
- Dix 10 pts
- Roi 4 pts
- Reine 3 pts
- Valet 2 pts

Toutes les autres cartes valent 0 pt.
La victoire est remportée par la première équipe atteignant les 1000 pts.

Chaque partie doit se terminer par un arrêt volontaire, une victoire ou une défaite de l'utilisateur.

Quand l'utilisateur se connecte au site, une page d'accueil s'affiche avec un unique bouton "Start a game". Quand il appuie sur ce bouton, le site lui propose alors de soit cliquer sur un lien qui redirige l'utilisateur vers un site expliquant les règles du chibre, soit d'appuyer sur un bouton "Continuer". Ce dernier permet d'accéder à un nouvel écran sur lequel l'utilisateur peut choisir entre deux boutons, respectivement lancer une "partie locale" ou une "partie entre amis".Une fois la partie lancée, il est possible de jouer une partie de jass. Lorsque c'est le tour de l'utilisateur de choisir l'atout, quatre boutons apparaissent représentant les quatres couleurs des cartes. La couleur de l'atout choisi sera affichée pendant la partie. Les points sont ajoutés au score de l'équipe qui a remporté la plie à la fin de celle-ci. Le score courant est visible en permanence dans un encart situé en haut à gauche de l'écran de jeu. Les cartes jouées sont positionnées devant le joueur auquel elles appartiennent. Un bouton "quitter le jeu" est présent en tout temps en haut à droite de l'écran de jeu et permet d'abandonner la partie et revenir à la page d'accueil. A la fin de chaque manche, un tableau récapitulatif des scores est affiché avant de passer à la manche suivante. A l'issue de la partie, un écran indiquant les scores finaux ainsi que la vitoire ou la défaite de l'utilisateur apparaît. L'utilisateur peut alors cliquer n'importe où sur l'écran pour revenir à l'écran d'accueil et relancer une partie ou quitter le jeu.

Un exemple détaillé de l'utilisation du site est présenté dans les mockups situés ci-dessous, indiquant ainsi visuellement les principaux requirements du site du point de vue d'un utilisateur lambda.

### Client

Concernant le client de notre programme, il nous faudra :
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
- Svelte (Javascript FW)
- Hébergement sur Netlify

Backend
- Java
- Spring
- Docker
- Google Cloud Run

### Déploiement

#### Frontend

Le frontend est déployé sur Netlify, accessible actuellement via l'URL de [déploiement par défaut](https://radiant-cucurucho-67d710.netlify.app/).

Une action GitHub est configurée pour effectuer le déploiement sur Netlify automatiquement (génération de l'application avec npm puis chargement sur Netlify), depuis la branche `main` à l'issue de chaque push sur celle-ci.

#### Backend

Le backend est déployé sur Google Cloud Run, accessible actuellement via l'URL de [déploiement par défaut](https://server-xxcwjwt7qq-ue.a.run.app/).

Comme pour le frontend, une action GitHub est configurée pour effectuer le déploiement sur Google Cloud Run (packaging avec Maven, création et chargement de l'image Docker dans Google Container Registry), lors de chaque modification de la branche `main`.

### Outils utilisés sur le repository github

Nous avons décidé d'utiliser gitflow afin de travailler proprement sur différentes branches avec différents
niveaux de protection.
Nous avons configuré la branche main afin qu'elle ne puisse pas être modifiée par une autre action
qu'un Pull Request. Cette même PR doit être validée par une autre personne.
La branche *develop* est définie comme celle par défaut sur notre repository.
Le workflow gitflow consiste également en la création de la branche *develop* qui est celle sur laquelle
nous allons effectivement travailler. Lorsque nous souhaitons effectuer une release, il suffit de merge
la branche develop au *main*. Chaque fonctionnalité nécessite donc la création d'une nouvelle branche à partir
de *develop* et tout ajout de code se fait exclusivement sur les branches prévues à cet effet. Une fois la feature
terminée, une PR permet de la merge à la branche *develop*.

## Mockups

La page d'accueil de Javass est la suivante :
![](Img/page_accueil.jpg)

Lorsque vous appuyez sur "Start a Game", vous êtes menés à une page vous proposant de relire les règles
si vous êtes un profane ou que vous n'avez pas joué depuis longtemps.

![](Img/page_regles.jpg)

Une fois votre relecture des règles terminée, appuyer sur continuer affichera une page permettant de choisir
le type de partie souhaitée.

![](Img/page_choix_type_partie.jpg)

Avant de commencer à jouer chaque manche, chacun leur tour, les joueurs choisissent la couleur d'atout.

![](Img/page_choix_atout.jpg)

Une fois la partie lancée, les joueurs et votre main sont affichés. Les cartes jouées sont face contre ciel
au centre de l'écran et le score en haut à gauche.

![](Img/page_partie_en_cours.jpg)

A la fin de chaque plie, le score de l'équipe qui a remporté la plie est automatiquement mis à jour en y ajoutant les points de celle-ci

![](Img/page_partie_en_cours_2.jpg)

A la fin de chaque manche, un tableau récapitulatif des scores de la manche est affiché avant de passer à la manche suivante.

![](Img/tableau_score.jpg)

À l'issue de la partie les scores sont affichés et les vainqueurs désignés.

![](Img/page_victoire.jpg)

![](Img/page_defaite.jpg)

## Landing page

*Fichier joint*

## Fonctionnalités espérées

Voici les fonctionnalités minimum qui seront ajoutées au projet durant ces trois semaines.

- Les 36 cartes sont mélangées.
- Les 36 cartes sont distribuées aux 4 joueurs (donc 9 par personne).
- Chaque joueur pose une carte par tour.
- La carte la plus forte emporte la plie.
- Si notre coéquipier gagne la plie, le score s'incrémente.
- Le score s'incrémente du nombre de points selon ce qui est écrit au-dessus.
- Le joueur choisit l'atout.
- L'atout l'emporte sur les trois autres familles.
- Le buur puis le nell sont les deux plus fortes cartes.
- Le joueur est obligé de suivre la famille qui a été posé en premier.
  - Exception si le joueur n'a pas la famille qui a été posé. Il peut poser n'importe quelle autre carte.
  - Le joueur peut couper avec un atout si une autre famille a été posée.
  - La sous-coupe est interdite. Si quelqu'un coupe on ne peut pas mettre un atout plus faible. Sauf si c'est notre dernière carte.

### Si le temps le permet

- Jouer en ligne avec des joueurs.
- Ajouter le chibre (demander à son coéquipier de choisir l'atout pour nous).
- Ajouter les annonces. Au début, on annonce des combinaisons spéciales de cartes pour gagner plus de points.
- Rendre les bots plus intelligent.

## Méthodologie

Afin de mener à bien ce projet, notre équipe a choisi de pratiquer une méthode agile SCRUM. Cette méthodologie
incrémentale correspond à notre dynamique de groupe. Nous allons donc effectuer ce travail sur 3 Sprints correspondant
à chaque semaine.

Chaque début de semaine, nous décomposerons le travail à faire en User stories, elles-mêmes possiblement
décomposées afin d'être partagées entre plusieurs membres du groupe. Chaque membre se voit attitrer (par un autre membre
ou lui-même) une story. Chaque membre doit effectuer trois estimations du temps que prendra la story.
Nous tenons toutefois à appliquer un peu le principe de pair programming si nécessaire.

À la fin d'un sprint, nous repasserons sur les estimations de temps et effectuerons un feedback général afin de
pouvoir améliorer ces aspects au fil des sprints. Si certaines stories ne sont pas achevée à terme du sprint
correspondant, il sera passé au prochain sprint avec la plus haute priorité.

