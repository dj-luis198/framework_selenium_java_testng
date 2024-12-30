package com.demoqa.utils.data_provider;

import com.demoqa.utils.external_file_utility.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class CheckBoxDataProvider {
        @DataProvider(name = "checkBoxData", parallel = false)
        public String[][] textBoxData() throws IOException {
            String path = "src/test/resources/csv/check_box.csv";
            String delimiter = ";";
            return CSVReader.readCSV( path, delimiter);
        }
}
