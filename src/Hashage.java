import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashage {

    public static String hashMD5(String text) throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = text.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] array = messageDigest.digest(bytesOfMessage);

        StringBuilder buffer = new StringBuilder();
        for (byte b : array) {
            buffer.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return buffer.toString();
    }
}
