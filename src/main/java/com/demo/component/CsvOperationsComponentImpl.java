package com.demo.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvOperationsComponentImpl implements CsvOperationsComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvOperationsComponentImpl.class);
    private List<String> lines;

    public CsvOperationsComponentImpl() {
        LOGGER.info("*** Dziala konstruktor CsvOperationsComponentImpl() ***");
        lines = new ArrayList<>();
    }

    @Override
    public List<String> readCsvFile(String fileName, String divider) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH + fileName));

            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
