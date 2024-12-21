package com.demoqa.utils.external_file_utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {

   public Properties initProperties(String name) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("./src/main/resources/propertiesFiles/" + name + ".properties")) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
    }
