import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Menu options:");
        System.out.println("1. Hash MD5");
        System.out.println("2. Some menu option");
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
        System.out.println(propertyValues.getPropValues("taille_min"));
        System.out.println(propertyValues.getPropValues("taille_max"));

        switch (choice) {
            case 1:
                System.out.println("Hash MD5 of \"" + propertyValues.getPropValues("testtext") +
                        "\": " + Hashage.hashMD5(propertyValues.getPropValues("testtext")));
            case 2:

        }
    }
}
