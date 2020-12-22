package com.demo.component;

import java.util.List;

public interface CsvOperationsComponent {

    String PATH = "C:/Users/ideapad/Desktop/java/toik/toik-lab12/src/main/resources/";

    List<String> readCsvFile(String fileName, String divider);
}
