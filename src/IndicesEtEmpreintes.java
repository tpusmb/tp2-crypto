import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * Class pour manipuler des indices
 */
public class IndicesEtEmpreintes {

    // Liste des valeurs caluler pour i2c
    private int[] vals;
    private String alphabet;
    private int taille_min, taille_max;

    /**
     * Constructeur qui pre calcule les valeurs pour i2c
     *
     * @param alphabet
     * @param taille_min
     * @param taille_max
     */
    public IndicesEtEmpreintes(String alphabet, int taille_min, int taille_max) {
        this.alphabet = alphabet;
        this.taille_min = taille_min;
        this.taille_max = taille_max;
        int nb_test = (this.taille_max - this.taille_min) + 1;
        this.vals = new int[nb_test];
        int acc = 0;
        // Pre calcule des valeurs pour i2c
        for (int i = taille_min; i <= taille_max; i++) {
            this.vals[acc] = (int) Math.pow(this.alphabet.length(), i);
            acc++;
        }
    }

    /**
     * Hashage vers empreintes
     *
     * @param y byte codes fait par le hashage
     * @param t colomne
     * @param N nombre total de textes clairs valides
     * @return empreintes
     */
    public static long h2i(byte[] y, int t, long N) {

        BigInteger yInt = Utils.byteToInt(y);
        return yInt.add(BigInteger.valueOf(t)).mod(BigInteger.valueOf(N)).longValue();

    }

    /**
     * Indices vers text claire
     *
     * @param number Indices
     * @return text claire
     */
    public String i2c(int number) {

        int taille_mot = this.taille_min;
        for (int val : this.vals) {
            if (number < val)
                break;
            number -= val;
            taille_mot++;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < taille_mot; i++) {
            // Ajoue du char
            str.insert(0, alphabet.charAt(number % this.alphabet.length()));
            number = number / this.alphabet.length();
        }
        return str.toString();
    }

    /**
     * Indices vers empreintes
     *
     * @param number I2c number to get String
     * @param index  H2i index
     * @param N      nombre total de textes clairs valides
     * @return empreintes
     * @throws NoSuchAlgorithmException
     */
    public long i2i(int number, int index, long N) throws NoSuchAlgorithmException {
        return h2i(Hashage.hashMD5(i2c(number)), index, N);
    }

}
