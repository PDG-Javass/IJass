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

Toutes les autres cartes valent 0 pts.
La victoire est remportée par la première équipe atteignant les 1000 pts.

Chaque partie doit se terminer par un arrêt volontaire, une victoire ou une défaite de l'utilisateur.

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
- (Hébergement à choisir) Heroku, Netlify

Backend
- Java
- Spring
- Docker
- Google Cloud Run

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
![](/Img/page_accueil.jpg)

Lorsque vous appuyez sur "Start a Game", vous êtes menés à une page vous proposant de relire les règles
si vous êtes un profane ou que vous n'avez pas joué depuis longtemps.

![](/Img/page_regles.jpg)

Une fois votre relecture des règles terminée, appuyer sur continuer affichera une page permettant de choisir
le type de partie souhaitée.

![](/Img/page_choix_type_partie.jpg)

Avant de commencer à jouer chaque manche, chacun leur tour, les joueurs choisissent la couleur d'atout.

![](/Img/choix_atout.jpg)

Une fois la partie lancée, les joueurs et votre main sont affichés. Les cartes jouées sont face contre ciel
au centre de l'écran et le score en haut à gauche.

![](/Img/page_partie_en_cours.jpg)

![](/Img/page_partie_en_cours_2.jpg)

Il est possible de zoomer sur le tableau des scores.

![](/Img/tableau_score.jpg)

À l'issue de la partie les scores sont affichés et les vainqueurs désignés.

![](/Img/page_victoire.jpg)

![](/Img/page_defaite.jpg)

## Landing page

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
