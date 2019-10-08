import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyValues {

    private InputStream inputStream;
    private Properties properties;

    public PropertyValues() throws IOException {

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

    public String getPropValues(String propName) {
        return this.properties.getProperty(propName);
    }

}
