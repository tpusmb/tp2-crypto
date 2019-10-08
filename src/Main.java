import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PropertyValues propertyValues = new PropertyValues();
        System.out.println(propertyValues.getPropValues("taille_min"));
        System.out.println(propertyValues.getPropValues("taille_max"));
    }
}
