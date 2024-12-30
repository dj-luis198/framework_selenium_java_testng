package com.demoqa.utils.data_provider;

import com.demoqa.utils.external_file_utility.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TextBoxDataProvider {
    @DataProvider(name = "textBoxData", parallel = false)
    public String[][] textBoxData() throws IOException {
        String path = "src/test/resources/csv/text_box.csv";
        String delimiter = ",";
        return CSVReader.readCSV( path, delimiter);
    }
}
