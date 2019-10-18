# tp2-crypto

## Installation

Pour compiler:

    javac src/*.java -d out
    
Une fois les fichiers java compilés, il faut copier le fichier config `resources/config.properties.default` dans le dossier out et le renomer en `config.properties`.

Enfin pour lancer le programme:

    java Main
    
**Note:** Pendant les tests, il n'est pas nécessaire de relancer le programme pour que les modifications dans le fichier `config.properties` soient pris en compte.

## Questions faites

### Question 1

1. La recherche dichotomique est de complexité log2(n) puis nous appliquons n fois la recherche dichotomique (largeur du tableau)
   ce qui donne O(n)=n*log2(n).<br>L'espace utilisé par le tableau généré fait 2 fois sa hauteur.

2. Pour la recherche exhaustive on a une complexité de O(n) = n*O(h) où O(h) est la complexité de la fonction de hashage.
   **Note:** L'espace utilisé n'est que d'un élément de hashage.<br>Pour le pré-calcul, la complexité est
   O(n) = n*O(h) sauf que nous sauvegardons  la totalité du tableau, mais la recherche passe en complexité O(n) = log2(n) si on utilise une recherche dichotomique.

### Question 6

Le paramètre t augmente la couverture de la table car cela permet de changer de colonne et de réduire le nombre de collision possible.

### Question 10

La recherche dichotomique est de complexité log2(n), puis nous l’apliquons n fois où n est la largeur de notre table. Ce qui done O(n) = n*log2(n).

### Resultat

Les resultats avec différents algo de [rechercher](RESULT.md)

Comme vous pouvez le voir sur un alphabet `ABCDEFGHIJKLMNOPQRSTUVWXYZ` nous n'arrivons pas a trouver l'inverse du hash, et n'avons pas réussi a trouver pourquoi.<br>
Alors que sur le fichier config par defaut nous n'avons pas de problème à trouver l'inverse.