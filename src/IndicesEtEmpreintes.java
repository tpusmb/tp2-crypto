import java.util.Arrays;

public class IndicesEtEmpreintes {

    public IndicesEtEmpreintes(int taille_min, int taille_max) {
        int[] min_val;
        int[] max_val;
        for(int i = taille_min; i < taille_max; i++){

        }
    }

    public static int h2i(byte[] y, int t, int N) {

        int y_truck = Utils.byteToInt(Arrays.copyOfRange(y, 0, 8));
        return (y_truck + t) % N;

    }

}
