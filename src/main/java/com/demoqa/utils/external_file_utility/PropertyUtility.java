package com.demoqa.utils.external_file_utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {
    private final Logger logger = LogManager.getLogger(PropertyUtility.class);

    public Properties initProperties(String name) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("./src/main/resources/propertiesFiles/" + name + ".properties")) {
            prop.load(fis);
            return prop;
        } catch (IOException e) {
            logger.error("Properties file not loaded", e);
            return new Properties();
        }
    }
}
