package com.demoqa.tests.elements.data_provider;

import com.demoqa.utils.exceptions.FileException;
import com.demoqa.utils.external_file_utility.CSVReader;
import org.testng.annotations.DataProvider;

public class TextBoxDataProvider {
    @DataProvider(name = "textBoxData", parallel = false)
    public String[][] textBoxData() throws FileException {
        String path = "src/test/resources/csv/text_box.csv";
        String delimiter = ",";
        return CSVReader.readCSV( path, delimiter);
    }
}
