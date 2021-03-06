import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class pour lire un fichier config (config.properties)
 */
class PropertyValues {

    private InputStream inputStream;
    private Properties properties;

    PropertyValues() throws IOException {

        try {
            this.properties = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        } finally {
            assert inputStream != null;
            inputStream.close();
        }

    }

    /**
     * Obtien la valeur lier aux prop name
     *
     * @param propName nom de la property
     * @return valeur lier
     */
    String getPropValues(String propName) {
        return this.properties.getProperty(propName);
    }

}
