import java.util.Arrays;

public class IndicesEtEmpreintes {

    private int[] min_val;
    private int[] max_val;
    private int[] nb_lettre;
    private double[] const_minus;
    private String alphabet;

    public IndicesEtEmpreintes(String alphabet, int taille_min, int taille_max) {
        this.alphabet = alphabet;
        int nb_test = (taille_max - taille_min) + 1;
        this.min_val = new int[nb_test];
        this.max_val = new int[nb_test];
        this.const_minus = new double[nb_test];
        this.nb_lettre = new int[nb_test];
        double acc = 0;
        int nb_lettre_acc = 0;
        for(int i = 0; i < nb_test; i++){
            min_val[i] = (int) Math.round(Math.pow(alphabet.length(), nb_lettre_acc));
            max_val[i] = (int) Math.round(Math.pow(alphabet.length(), nb_lettre_acc + 1));
            const_minus[i] = acc;
            nb_lettre[i] = nb_lettre_acc;
            acc += min_val[i];
            nb_lettre_acc++;
        }
    }

    public static int h2i(byte[] y, int t, int N) {

        int y_truck = Utils.byteToInt(Arrays.copyOfRange(y, 0, 8));
        return (y_truck + t) % N;

    }


    public String i2c(int i){

        int norm_i = i;
        int nb_lettre = 0;
        for(int tab_index = 0; tab_index < this.max_val.length; tab_index++){
            if(this.min_val[tab_index] <= i && i < this.max_val[tab_index]){
                norm_i -= this.const_minus[tab_index];
                nb_lettre = this.nb_lettre[tab_index];
                break;
            }
        }
        StringBuilder res = new StringBuilder();
        for(int loop = 0; loop < nb_lettre; loop++){
            res.append(this.alphabet.charAt(norm_i % this.alphabet.length()));
            norm_i /= this.alphabet.length();
        }
        return res.reverse().toString();
    }
}
