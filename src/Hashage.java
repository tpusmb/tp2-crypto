import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class pour implementer des algo de hash
 */
public class Hashage {

    /**
     * Hashage md5
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     */
    static byte[] hashMD5(String text) throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = text.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return messageDigest.digest(bytesOfMessage);
    }
}
