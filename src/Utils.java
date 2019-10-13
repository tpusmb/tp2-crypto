import java.math.BigInteger;
import java.nio.ByteBuffer;

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
}
