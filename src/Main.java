import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

public class Main {
    static final int PROGRAM_EXIT = 0;
    static final int MAX_CHOICE = 5;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n\nMenu options:");
            System.out.println("1. Hash MD5");
            System.out.println("2. h2i");
            System.out.println("3. i2c");
            System.out.println("4. Génération ou lecture d'unr RainBow table");
            System.out.println("5. Craquer un hashage");
            System.out.println("0. Exit the program");
            System.out.print("\nPlease select an option from 1-5\r\n");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try {
                int input = Integer.parseInt(br.readLine());

                if (input < 0 || input > MAX_CHOICE) {
                    System.out.println("You have entered an invalid selection, please try again\r\n");
                } else if (input == PROGRAM_EXIT) {
                    System.out.println("You have quit the program\r\n");
                    System.exit(1);
                } else {
                    System.out.println("You have entered " + input + "\r\n");
                    process(input);
                }
            } catch (Exception e) {
                System.out.println("Error trying to read your input!\r\n");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private static void process(int choice) throws Exception {
        PropertyValues propertyValues = new PropertyValues();
        int taille_min = Integer.parseInt(propertyValues.getPropValues("taille_min"));
        int taille_max = Integer.parseInt(propertyValues.getPropValues("taille_max"));
        String textTestMD5 = propertyValues.getPropValues("testMD5");
        String textTesth2i = propertyValues.getPropValues("test_chaine_h2i");
        taille_min = Math.min(taille_min, taille_max);
        taille_max = Math.max(taille_min, taille_max);
        String alphabet = propertyValues.getPropValues("alphabet");
        long N = Utils.getN(taille_min, taille_max, alphabet.length());
        int hauteur_rainbow_table = Integer.parseInt(propertyValues.getPropValues("hauteur_rainbow_table"));
        int largeur_rainbow_table = Integer.parseInt(propertyValues.getPropValues("largeur_rainbow_table"));
        boolean read_table = Boolean.getBoolean(propertyValues.getPropValues("read_table_file"));
        boolean recherche_exhaustive = Boolean.getBoolean(propertyValues.getPropValues("recherche_exhaustive"));
        RainBowTable rainBowTable;
        System.out.println("N = " + N);
        System.out.println("Estimation couverture: " + Utils.couverture_estimation(hauteur_rainbow_table,
                largeur_rainbow_table, (int) N) + '%');
        switch (choice) {
            case 1:
                System.out.println("Hash MD5 of \"" + textTestMD5 + "\": " + Utils.byteToString(Hashage.hashMD5(textTestMD5)));
                break;
            case 2:
                long h2i_res = IndicesEtEmpreintes.h2i(Hashage.hashMD5(textTesth2i),
                        Integer.parseInt(propertyValues.getPropValues("t_h2i_test")),
                        N);
                System.out.println("H2I test : " + h2i_res);
                break;
            case 3:
                IndicesEtEmpreintes indicesEtEmpreintes = new IndicesEtEmpreintes(alphabet, taille_min, taille_max);
                int i2c_id = Integer.parseInt(propertyValues.getPropValues("test_i2c"));
                System.out.println("I2C test with id " + i2c_id + ": " + indicesEtEmpreintes.i2c(i2c_id));
                break;
            case 4:
                rainBowTable = Main.getRainBowTable(read_table, propertyValues,
                        largeur_rainbow_table, hauteur_rainbow_table,
                        taille_min, taille_max, alphabet, N);
                System.out.println(rainBowTable.toString());
            case 5:
                long debut = System.currentTimeMillis();
                rainBowTable = Main.getRainBowTable(read_table, propertyValues,
                        largeur_rainbow_table, hauteur_rainbow_table,
                        taille_min, taille_max, alphabet, N);
                long debut_2 = System.currentTimeMillis();
                String res = rainBowTable.inverse(Hashage.hashMD5(
                        propertyValues.getPropValues("test_inverse_text")), recherche_exhaustive);
                System.out.println("Text a retrouver: " + propertyValues.getPropValues("test_inverse_text"));
                System.out.println("config\n Largeur X Hauteur: " + largeur_rainbow_table + 'X' + hauteur_rainbow_table +
                        "\n Taille min = " + taille_min +
                        "\n Taille max = " + taille_max);
                System.out.print("Temps de calcule de inverse en sec: ");
                System.out.println((System.currentTimeMillis() - debut_2) * 0.001);
                System.out.print("Temps de calcule en sec: ");
                System.out.println((System.currentTimeMillis() - debut) * 0.001);

                if (res != null) {
                    System.out.println("Text trouver: " + res);
                } else
                    System.out.println("Pas de text trouver :(");
                System.out.println("\n-------------------------------------");

        }
    }

    static RainBowTable getRainBowTable(boolean read_table, PropertyValues propertyValues,
                                        int largeur, int hauteur,
                                        int taille_min, int taille_max,
                                        String alphabet, long N) throws NoSuchAlgorithmException {
        System.out.println(hauteur);
        if (hauteur > N)
            throw new IllegalArgumentException("La hauteur de la list est supèrieur au nombre total de textes clairs valides N");
        RainBowTable rainBowTable;
        if (read_table) {
            rainBowTable = Utils.readRainBowTable(propertyValues.getPropValues("rainbow_table_file_name"));
        } else {
            rainBowTable = new RainBowTable(largeur, hauteur,
                    alphabet, taille_min, taille_max, N);
            Utils.saveRainBowTable(rainBowTable, propertyValues.getPropValues("rainbow_table_file_name"));
        }
        return rainBowTable;
    }
}
