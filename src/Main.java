import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Menu options:");
        System.out.println("1. Hash MD5");
        System.out.println("2. test i2c");
        System.out.println("3. Some menu option");
        System.out.println("4. Some menu option");
        System.out.println("5. Exit the program");
        System.out.print("\nPlease select an option from 1-5\r\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int input = Integer.parseInt(br.readLine());

            if (input < 0 || input > 5) {
                System.out.println("You have entered an invalid selection, please try again\r\n");
            } else if (input == 5) {
                System.out.println("You have quit the program\r\n");
                System.exit(1);
            } else {
                System.out.println("You have entered " + input + "\r\n");
                process(input);
            }
        } catch (Exception e) {
            System.out.println("Error trying to read your input!\r\n");
            System.exit(1);
        }
    }

    private static void process(int choice) throws Exception {
        PropertyValues propertyValues = new PropertyValues();
        int taille_min = Integer.parseInt(propertyValues.getPropValues("taille_min"));
        int taille_max = Integer.parseInt(propertyValues.getPropValues("taille_max"));
        taille_min = Math.min(taille_min, taille_max);
        taille_max = Math.max(taille_min, taille_max);
        String alphabet = propertyValues.getPropValues("alphabet");

        switch (choice) {
            case 1:
                System.out.println("Hash MD5 of \"" + propertyValues.getPropValues("testtext") +
                        "\": " + Hashage.hashMD5(propertyValues.getPropValues("testtext")));
                break;
            case 2:
                IndicesEtEmpreintes indicesEtEmpreintes = new IndicesEtEmpreintes(alphabet, taille_min, taille_max);
                int i2c_id = Integer.parseInt(propertyValues.getPropValues("test_i2c"));
                System.out.println("I2C test with id " + i2c_id + ": " + indicesEtEmpreintes.i2c(i2c_id));

        }
    }
}
