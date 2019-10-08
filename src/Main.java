
public class Main {

    public static void main(String[] args) throws Exception {
        PropertyValues propertyValues = new PropertyValues();
        System.out.println(propertyValues.getPropValues("taille_min"));
        System.out.println(propertyValues.getPropValues("taille_max"));

        System.out.println(Hashage.hashMD5(propertyValues.getPropValues("testtext")));
    }

}
