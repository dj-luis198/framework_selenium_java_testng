package com.demoqa.utils.external_file_utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    private static final Logger logger = LogManager.getLogger(CSVReader.class);
    private CSVReader() {}

    public static String[][] readCSV(String filePath, String delimiter) throws IOException {
        String[][] data;
        String line;
        int rows = 0;
        int cols = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (cols == 0) {
                    cols = values.length;
                }
                rows++;
            }
        } catch (IOException e) {
            logger.error("read or write error",e);
            throw new IOException();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            data = new String[rows][cols];
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(delimiter);
                System.arraycopy(values, 0, data[row], 0, cols);
                row++;
            }
        } catch (IOException e) {
            logger.error("read or write error",e);
            throw new IOException();
        }
        return data;
    }
}
