import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

public class RainBowTable implements Serializable {

    private int largeur, hauteur, taille_min, taille_max;
    private IndicesEtEmpreintes indicesEtEmpreintes;
    private ArrayList<RainBowCell> rainBowTable;
    private long N;
    private String alphabet;

    /**
     * @param largeur    Largeur du tableau
     * @param hauteur    Hauteur du tableau
     * @param alphabet   Alphabet
     * @param taille_min taille min des mots
     * @param taille_max taille max des mots
     * @param N          nombre total de textes clairs valides
     * @throws NoSuchAlgorithmException Pour le hashage
     */
    public RainBowTable(int largeur, int hauteur, String alphabet,
                        int taille_min, int taille_max, long N) throws NoSuchAlgorithmException {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.N = N;
        this.alphabet = alphabet;
        this.taille_min = taille_min;
        this.taille_max = taille_max;
        this.indicesEtEmpreintes = new IndicesEtEmpreintes(alphabet, this.taille_min, this.taille_max);
        this.rainBowTable = new ArrayList<>();
        int rand_index;
        int i2i_acc;
        ArrayList<Integer> done_index = new ArrayList<>();
        for (int i = 0; i < hauteur; i++) {

            // Obtention d'un index aux hazzard
            rand_index = Utils.index_aleatoire((int) this.N);
            while (done_index.contains(rand_index))
                rand_index = Utils.index_aleatoire((int) this.N);
            done_index.add(rand_index);
            i2i_acc = rand_index;
            // Creation des empreintes H(H(n)))
            for (int j = 1; j < largeur; j++)
                i2i_acc = (int) this.indicesEtEmpreintes.i2i(i2i_acc, j, this.N);
            this.rainBowTable.add(new RainBowCell(rand_index, i2i_acc));
        }
        // Sort by i2i_value value
        Collections.sort(this.rainBowTable);
    }

    /**
     * Recherche si idx est dans notre rainbow table a l'aide de la recherche dichotomique
     *
     * @param idx id a trouver
     * @return numéros des premières et dernières lignes dont les dernières colonnes sont égale à idx [start, end]
     */
    private int[]recherche_dichotomique(long idx){
        int bas = 1, haut = this.rainBowTable.size() - 1, milieu;
        int rang = -1;
        do {
            milieu = (bas + haut) / 2;
            if (idx == this.rainBowTable.get(milieu).getI2i_value()) rang = milieu;
            else if (this.rainBowTable.get(milieu).getI2i_value() < idx) bas = milieu + 1;
            else haut = milieu - 1;
        }
        while ((idx != this.rainBowTable.get(milieu).getI2i_value()) & (bas <= haut));
        if (rang == -1)
            return new int[]{-1, -1};
        // Obtention de la plage des valeur egale a idx
        int start_rang = rang - 1, end_rang = rang + 1;
        while (start_rang >= 0 && this.rainBowTable.get(start_rang).getI2i_value() == idx)
            start_rang--;
        start_rang++;
        while (end_rang < this.rainBowTable.size() && this.rainBowTable.get(end_rang).getI2i_value() == idx)
            end_rang++;
        end_rang--;
        return new int[]{start_rang, end_rang};
    }

    /**
     * Recherche si idx est dans notre rainbow table
     *
     * @param idx id a trouver
     * @return numéros des premières et dernières lignes dont les dernières colonnes sont égale à idx [start, end]
     */
    private int[] recherche_exhaustive(long idx) {
        int start_rang = -1;
        int end_rang;
        for (int i = 0; i < this.rainBowTable.size(); i++) {
            if (this.rainBowTable.get(i).getI2i_value() == idx) {
                start_rang = i;
                break;
            }
        }
        if (start_rang == -1)
            return new int[]{-1, -1};

        end_rang = start_rang;
        while (end_rang < this.rainBowTable.size() && this.rainBowTable.get(end_rang).getI2i_value() == idx)
            end_rang++;
        end_rang--;
        return new int[]{start_rang, end_rang};
    }

    /**
     * Recherche si idx est dans notre rainbow table
     * @param idx id a trouver
     * @param recherche_exhaustive True la recherche exhaustive sera utiliser si False ce sera la recherche dichotomique
     * @return
     */
    private int[] recherche(long idx, boolean recherche_exhaustive) {
        if(recherche_exhaustive)
            return recherche_exhaustive(idx);
        else
            return recherche_dichotomique(idx);
    }

    /***
     * Verifie si le hashage de idx = h
     * @param h hash a craquer
     * @param t H2i index
     * @param idx id a comparer
     * @return null le candidat n'est pas egale sinon son format text claire
     * @throws NoSuchAlgorithmException Pour le hashage
     */
    private String verifie_candidat(byte[] h, int t, int idx) throws NoSuchAlgorithmException {
        for (int i = 1; i < t; i++)
            idx = (int) this.indicesEtEmpreintes.i2i(idx, t, this.N);
        String clair = this.indicesEtEmpreintes.i2c(idx);
        if (Utils.byteToInt(h).equals(Utils.byteToInt(Hashage.hash(clair))))
            return clair;
        return null;
    }

    /**
     * @param h Hash a craquer
     * @param  recherche_exhaustive True la recherche exhaustive sera utiliser si False ce sera la recherche dichotomique
     * @return null on n'a pas réussi a trouver la version text claire sinon le text claire
     * @throws NoSuchAlgorithmException Pour le hashage
     */
    public String inverse(byte[] h, boolean recherche_exhaustive) throws NoSuchAlgorithmException {
        int nb_candidats = 0;
        long idx;
        int[] rang;
        String result;
        // On parcoure nos colomne de notre table rainbow
        for (int colomne = this.largeur - 1; colomne > 0; colomne--) {
            // Obtention de empreintes de h
            idx = IndicesEtEmpreintes.h2i(h, colomne, this.N);
            // Calcule des empreintes recursive
            for (int index = colomne + 1; index < largeur; index++) {
                idx = this.indicesEtEmpreintes.i2i((int) idx, index, this.N);
            }
            // recherchd si idx dans notre table rainbow
            rang = recherche(idx, recherche_exhaustive);
            if (rang[0] != -1) {
                // a partire du rang trouver on regarde si il n'y a pas le maim hash que celui a craquer
                for (int i = rang[0]; i <= rang[1]; i++) {
                    result = this.verifie_candidat(h, colomne, this.rainBowTable.get(i).getIndex());
                    if (result != null)
                        return result;
                    else
                        nb_candidats++;
                }
            }
        }
        System.out.println("- Nb candidats: " + nb_candidats);
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.rainBowTable.size(); i++) {
            stringBuilder.append(i).append(": ").append(rainBowTable.get(i).getIndex()).
                    append(" --> ").append(rainBowTable.get(i).getI2i_value()).append("\n");
        }
        return stringBuilder.toString();
    }
}
