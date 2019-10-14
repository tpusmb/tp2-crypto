import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Random;

class Utils {

    static BigInteger byteToInt(byte[] bytes){
        BigInteger res = BigInteger.ZERO;
        BigInteger _256 = BigInteger.valueOf(256);
        for (int i = 0; i < 8; i++){
            res = res.add(BigInteger.valueOf(bytes[i] & 0xFF).multiply(_256.pow(i)));
        }
        return res;
    }

    static String byteToString(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte b : bytes) {
            buffer.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return buffer.toString();
    }

    static long getN(int taille_min, int taille_max, int alphabet_length){
        long N = 0;
        for(int i = taille_min; i <= taille_max; i++){
            N += Math.pow(alphabet_length, i);
        }
        return N;
    }

    static int index_aleatoire(int N){
        Random r = new Random();
        return r.nextInt((N) + 1);
    }
}
