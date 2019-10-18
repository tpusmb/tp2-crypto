# tp2-crypto

## Installation

our compiler le projet:

    javac src/Main

Une fois les fichiers java compiler il faut copier le fichier config `resources/config.properties.default` dans le maime dossier.
Le renommer en `config.properties`

Pour démmarer le programme

    java main

## Question fait

### Question 1

1. La recherche dichotomique est de complexité log2(n) puis nous appliquons n fois la recherche dichotomique (largeur du tableau)
   ce qui donne O(n)=n*log2(n).<br>L'espace utilisé 2 x la hauteur du tableau générer

2. Pour la recherche exhaustive on n'a une complexité de O(n) = n*O(h) ou O(h) est la complexité de la fonction de hachage.
   **Note** la l'espace utilisé est que d'un élément de hachage.<br>Pour le précalcul nous somme aussi avec le complexité
   O(n) = n*O(h) sauf que nous sauvons la totalité du tableau mais la recherche passe en complexité O(n) = log2(n) si on utilise une recherche dichotomique

### Question 6

Le paramètre t augmente la couverture de la table car cela permet de changer de colonne est de réduire le nombre de collision possible

### Question 10

La recherche dichotomique est de complexité log2(n) puis nous l’apliquon n fois ou n est la largeur de notre table. Ce qui done O(n) = n*log2(n)

### Resultat

Les resultats avec différent algo de [rechercher](RESULT.md)

Comme vous pouvez le voire sur un alphabet `ABCDEFGHIJKLMNOPQRSTUVWXYZ` nous n'arrions pas a trouver l'inverse du hash on n'a pas réussi a trouver pour quoi.<br>
alors que sur le fichier config par defaut nous n'avons pas de problème a trouver l'inverse.