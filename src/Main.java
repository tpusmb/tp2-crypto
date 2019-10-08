import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        PropertyValues propertyValues = new PropertyValues();
        System.out.println(propertyValues.getPropValues("taille_min"));
        System.out.println(propertyValues.getPropValues("taille_max"));
    }

}
