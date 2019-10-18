# tp2-crypto

## Installation

Une fois les fichiers java compiler il faut copier le fichier config `resources/config.properties` dans le maime dossier.

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
