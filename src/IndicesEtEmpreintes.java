import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class IndicesEtEmpreintes {

    private int[] vals;
    private String alphabet;
    private int taille_min, taille_max;

    public IndicesEtEmpreintes(String alphabet, int taille_min, int taille_max) {
        this.alphabet = alphabet;
        this.taille_min = taille_min;
        this.taille_max = taille_max;
        int nb_test = (this.taille_max - this.taille_min) + 1;
        this.vals = new int[nb_test];
        int acc = 0;
        for (int i = taille_min; i <= taille_max; i++) {
            this.vals[acc] = (int) Math.pow(this.alphabet.length(), i);
            acc++;
        }
    }

    /**
     * @param y
     * @param t colonne
     * @param N
     * @return
     */
    public static long h2i(byte[] y, int t, int N) {

        BigInteger yInt = Utils.byteToInt(y);
        return yInt.add(BigInteger.valueOf(t)).mod(BigInteger.valueOf(N)).longValue();

    }

//    public static int i2i(int index, String clearText) throws NoSuchAlgorithmException {
//        int h2iResult = h2i(Hashage.hashMD5(clearText), index, 8);
//        String i2cResult = "";
//
//        return 0;
//    }

    public String i2c(int number) {

        int taille_mot = this.taille_min;
        for (int val : this.vals) {
            if (number < val)
                break;
            number -= val;
            taille_mot++;
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < taille_mot; i++){
            // Ajoue du char
            str.insert(0, alphabet.charAt(number % this.alphabet.length()));
            number = number / this.alphabet.length();
        }
        return str.toString();
    }

}
