package Data;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Data {
    private Properties properties;
    public Data(){
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/Data/data.properties")) {
            properties.load(fileInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String getValueProperty(String key){
        return properties.getProperty(key);
    }
}
