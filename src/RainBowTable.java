import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

public class RainBowTable {

    private int largeur, hauteur;
    private IndicesEtEmpreintes indicesEtEmpreintes;
    private ArrayList<RainBowCell> rainBowTable;

    public RainBowTable(int largeur, int hauteur, String alphabet,
                        int taille_min, int taille_max, long N) throws NoSuchAlgorithmException {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.indicesEtEmpreintes = new IndicesEtEmpreintes(alphabet, taille_min, taille_max);
        this.rainBowTable = new ArrayList<>();
        int rand_index;
        int i2i_acc;
        for (int i = 0; i < hauteur; i++) {
            rand_index = Utils.index_aleatoire((int) N);
            i2i_acc = rand_index;
            for (int j = 1; j < largeur; j++) {
                i2i_acc = (int) this.indicesEtEmpreintes.i2i(i2i_acc, j, N);
            }
            this.rainBowTable.add(new RainBowCell(rand_index, i2i_acc));
        }
        // Sort by i2i_value value
        Collections.sort(this.rainBowTable);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.rainBowTable.size(); i++) {
            stringBuilder.append(i + ": " + rainBowTable.get(i).getIndex() +
                    " --> " + rainBowTable.get(i).getI2i_value() + "\n");
        }
        return stringBuilder.toString();
    }
}
