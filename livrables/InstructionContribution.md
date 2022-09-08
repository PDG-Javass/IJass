# Comment contribuer au projet Javass

## GitHub

Si vous souhaitez ajouter des fonctionnalités à Javass voici la marche à suivre :
- Cloner le repository git en local
- Afin de travailler proprement, créer une nouvelle branche depuis develop sur laquelle
travailler
- Implémenter votre fonctionnalité sur cette nouvelle branche
- Une fois satisfait de votre travail, effectuer une Pull Request de votre branche à develop

Dans le cas ou vous souhaiteriez avoir le plein contrôle sur votre repository, il vous suffit de fork notre repository.

## Ajouter une fonctionnalité à l'application

### Logique de Javass

La logique du jeu se trouve intégralement dans le répertoire [engine](https://github.com/PDG-Javass/IJass/tree/develop/server/src/main/java/ch/ijass/engine).
Si l'envie vous prend d'aller améliorer l'application ou d'ajouter des règles ou toutes autres entités dans la partie,
c'est dans les différentes classes présentes dans ce directory qu'il vous faut modifier du code ou ajouter
les classes que vous souhaitez. Actuellement le jeu possède la structure suivante :
- Cards : toutes les entités représentant les cartes et des ensembles de cartes
    * CardColor : type énuméré représentant les familles de cartes
    * CardValue : type énuméré représentant les valeurs des cartes
    * Deck : super classe de tous les conteneurs de cartes
      * DiscardDeck : les cartes ayant été jouées durant le round, mais n'étant plus sur le plateau
      * HandDeck : les cartes en main des joueurs
      * StartingDeck : le deck de départ de chaque round
- Players : les entités représentant les joueurs (Bots et personnes)
  * Player : classe abstraite représentant les joueurs
  * PersonPlayer : les joueurs nécessitant un input pour jouer
  * BotPlayer : les joueurs qui choisissent la meilleure carte à jouer selon un algorithme maison.
  * Team : classe représentant les deux équipes, permet de gérer les scores

### Autres aspects de l'application

Le serveur et le client communiquent au moyen de requêtes définies dans mappings. Leurs interactions consistent
en l'échange d'un état, correspondant à l'état courant de la partie. Les requêtes se trouvent dans la classe [Mappings](https://github.com/PDG-Javass/IJass/blob/develop/server/src/main/java/ch/ijass/server/Mappings.java).
