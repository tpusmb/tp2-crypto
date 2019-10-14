import java.math.BigInteger;
import java.util.Random;


class Utils {

    /**
     * Transform des bytes en int
     *
     * @param bytes
     * @return
     */
    static BigInteger byteToInt(byte[] bytes) {
        BigInteger res = BigInteger.ZERO;
        for (int i = 0; i < 8; i++) {
            res = res.add(BigInteger.valueOf(bytes[i] & 0xFF).multiply(BigInteger.valueOf(256).pow(i)));
        }
        return res;
    }

    /**
     * Transform des bytes en string
     *
     * @param bytes
     * @return
     */
    static String byteToString(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte b : bytes) {
            buffer.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return buffer.toString();
    }

    /**
     * Obtien le nombre total de textes clairs valides
     *
     * @param taille_min      Taille min d'un mots
     * @param taille_max      Taille max d'un mots
     * @param alphabet_length Longeur de l'alphabet
     * @return
     */
    static long getN(int taille_min, int taille_max, int alphabet_length) {
        long N = 0;
        for (int i = taille_min; i <= taille_max; i++) {
            N += Math.pow(alphabet_length, i);
        }
        return N;
    }

    /**
     * Obtien un nombre aléatoire compris entre 0 & N
     *
     * @param N nombre total de textes clairs valides
     * @return
     */
    static int index_aleatoire(int N) {
        Random r = new Random();
        return r.nextInt((N) + 1);
    }
}
