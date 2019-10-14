import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashage {

    static byte[] hashMD5(String text) throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = text.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return messageDigest.digest(bytesOfMessage);
    }
}
