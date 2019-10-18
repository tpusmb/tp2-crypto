import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class pour implementer des algo de hash
 */
public class Hashage {

    public static PropertyValues  properties;
    /**
     * Hachage
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     */
    static byte[] hash(String text) throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = text.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance(properties.getPropValues("fonction_hash"));
        return messageDigest.digest(bytesOfMessage);
    }
}
