# Projet de POO

Réalisation d'un jeu vidéo 2D : **UBomb**.


## Principes du jeu

Une princesse est détenue prisonnière par de méchants monstres verts. Votre mission, si vous l'acceptez, est d'aller la délivrer. Pour cela, vous devrez traverser plusieurs mondes, plus effrayants les uns que les autres. Des portes vous permettront de passer de mondes en mondes. Certaines portes seront fermées à clés et nécessiterons d'avoir une clé dans votre inventaire. Vous êtes un expert en explosif et utiliserez vos bombes pour détruire les obstacles devant vous et tuer les monstres qui vous attaquerons.


## Représentation du jeu

Chaque monde est représenté par une carte (rectangulaire) composée de cellules. Chaque cellule peut contenir :

-   le joueur, la princesse ou des monstres
-   des éléments de décors (arbres, pierres...) infranchissables et
    indestructibles ;
-   des caisses destructibles et déplaçables;
-   des portes, ouvertes ou fermées, permettant d’évoluer entre les
    mondes ;
-   des clefs pour débloquer les portes fermées ;
-   des bonus ou des malus qu'il est possible de ramasser

![Bombeirb](img/ubomb.png)

## Prise en main

Nous vous fournissons une première ébauche du jeu, utilisant la bibliothère JavaFX. Le lancement du jeu
fait apparaitre une [carte](img/bombeirb.png) minimaliste, chargée statiquement en mémoire, dans laquelle le joueur peut se déplacer dans toutes les directions quelque soit la nature des cellules.

Travail à fournir
=================


## Gestion des déplacements

Les mouvements du joueur sont limités par le cadre de la carte, les éléments de décors et les caisses. Les caisses doivent pouvoir être déplacées par le joueur si rien ne gêne dans le sens de la poussée. Le joueur ne peut déplacer qu'une seule caisse à la fois. Si un bonus se trouve dans la direction déplacement d’une caisse, la caisse reste bloquée. Le joueur ne peux pas déplacer deux caisses à la fois. Le joueur peut marcher sur une case où se trouve un bonnus, une clé, ou un autre personnage. 

## Gestion des mondes

Les cartes sont décrites dans des fichiers dans le dossier `world`. Nous définissons les conventions
suivantes :

-   Les cartes sont stockées sous forme de fichiers texte afin de
    pouvoir les créer et les modifier avec un simple éditeur de texte ;
-   Le nom de fichier d’une carte est de la forme `mapN.txt` ou `N` est le
    numéro du niveau;
-   La case en haut à gauche de la carte correspond aux coordonnées
    `(0,0)` ;
-   Chaque ligne correspond à une ligne de cellule sur la carte; 
-   Chaque cellule de la carte est définie en respectant le codage
définit dans le fichier `MapEntity.java`.


## Chargement des cartes

Modifiez la classe `Game` pour que le monde du jeu soit chargé depuis les fichiers du dossier `world`.


## Gestion des portes


Lorsque le joueur arrive sur la case d’une porte ouverte, il passe
automatiquement au niveau correspondant à cette porte (niveau supérieur
ou inférieur). Seul le niveau *0* n'a qu'une seule porte (on ne peut pas passer au niveau inférieur). Il se retrouve automatiquement sur la porte du niveau
correspondant. Si la porte est fermée, le joueur doit utiliser une des
clefs de son inventaire. Pour ce faire, il doit appuyer sur la touche `[ENTER]` lorsqu'il est à côté de la porte à ouvrir et qu'il regarde la porte. Une fois utilisée, la clé disparait de
l’inventaire. Chaque clef peut ouvrir indifféremment n’importe quelle
porte fermée. Une fois qu'une porte est ouverte, elle le reste pour toute la partie du jeu.

## Gestion du panneau d’informations

Le panneau d’information doit afficher le nombre de vies, le nombre de
bombes et leur portée, le nombre de clés dans l’inventaire et le numéro
de niveau courant.

## Gestion des bombes

Lorsque le joueur presse la touche `[ESPACE]`, il dépose une bombe sur
la case sur laquelle il se trouve, déclenchant une explosion au bout de
4 secondes. La mèche de la bombe diminue à chaque seconde. La portée de
la bombe est par défaut de 1 case, en croix (case du dessus, case du
dessous, case de gauche, case de droite). Les éléments de décor stoppent
la propagation de l’explosion dans le sens qu’ils obstruent. Si une caisse est sur le chemin de l’explosion, elle
disparait. Une explosion ne peut
détruire qu’une seule caisse dans une même direction. Si un bonus (ou un malus)se trouve sur le chemin de l’explosion, il disparait. 
Enfin, si un joueur ou un monstre est sur une cellule touchée par une explosion, il
perd une vie. Les explosions n’ont aucun effet sur les portes et les
clefs. Lorsque une bombe explose, une nouvelle bombe est ajoutée à
l’inventaire du joueur. 

Si le joueur pose une bombe et change ensuite de niveau en franchissant une porte, la bombe doit tout de même exploser au bout de 4 secondes. Les éléments de décors détruits sur un niveau doivent le rester pendant toute la durée de la partie.


## Gestion des bonus et malus

Les bonus et malus peuvent être sur la carte ou apparaitre lors de
l’explosion d’une caisse. Il en existe 5 :

-   **portée-** / **portée+** : ajoute/retire 1 unitée à la portée des bombes.
    La portée ne peut pas être nulle. Le changement de portée ne
    concerne que les bombes qui seront posées plus tard. Les bombes pour
    lesquelles la mèche est déjà allumée conservent leur portée
    initiale.

-   **bomb-** / **bomb+** : ajoute/enlève une bombe à l’inventaire. Le
    joueur dispose toujours d’au minimum 1 bombe.

-   **vie+** : ajoute une vie.

## Gestion des vies

Le joueur dispose de 3 vies au démarrage du jeu. Il peut en perdre s’il
se trouve sur une case à portée de l’explosion d’une bombe ou s'il croise un monstre. Si le joueur
n’a plus de vie, la partie se termine. Le joueur bénéficie alors d’une temporisation d'une seconde pendant laquelle il est invulnérable.

## Gestion des monstres

Les monstres peuvent être présents dès le chargement de la carte ou
apparaitre à l’explosion d’une caisse. Leurs déplacements sont
entièrement aléatoires. Une collision avec un monstre déclenche la perte
d’une vie. Commencez par ajouter un seul monstre à la fois, puis augmenter le
nombre de monstres. Les monstres ne peuvent pas ramasser les bonus qui se trouvent sur le
sol. Les monstres ont peur des portes et ne peuvent pas les franchir.

**Pour aller plus loin.** Faire en sorte que La vitesse de déplacement des monstres soit faible dans les premiers niveaux et augmente plus on se rapproche de la
princesse. Ajouter ensuite un module d’intelligence artificielle pour que les monstres des derniers niveaux se dirigent vers le joueur et non plus de manière aléatoire.

## Fin de partie

La partie est finit lorsque le joueur arrive sur la case de la
princesse. Les monstres ne veulent pas de mal à la princesse mais feront
tout pour la garder prisonnière. La touche `[ESCAPE]` permet de quitter la partie à tout moment.
