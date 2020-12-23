package com.demo.service.impl;

import com.demo.component.CsvOperationsComponent;
import com.demo.dto.DataResponse;
import com.demo.mapper.SudokuMapper;
import com.demo.service.SudokuCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SudokuCheckImpl implements SudokuCheck {
    private final short SIZE = 9;
    private final short SIZE_SMALL = 3;
    private final short ONE = 1;
    private CsvOperationsComponent csvOperationsComponent;
    private SudokuMapper sudokuMapper;

    @Autowired
    public SudokuCheckImpl(CsvOperationsComponent csvOperationsComponent, SudokuMapper sudokuMapper) {
        this.csvOperationsComponent = csvOperationsComponent;
        this.sudokuMapper = sudokuMapper;
    }


    @Override
    public Optional<DataResponse> checkBoard() {
        List<String> line = csvOperationsComponent.readCsvFile("sudoku.csv", ";");
        int[][] sudoku = sudokuMapper.convertLinesToBooks(line);
        boolean wrongSetNumber = false;
        DataResponse dataResponse = new DataResponse();


        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - ONE; j++) {
                for (int n = j + ONE; n < SIZE; n++) {
                    if (sudoku[i][j] == sudoku[i][n]) {
                        dataResponse.setLineIds(i + ONE);
                        wrongSetNumber = true;
                    }
                    if (sudoku[j][i] == sudoku[n][i]) {
                        dataResponse.setKolumnIds(i + ONE);
                        wrongSetNumber = true;
                    }
                }
            }
        }


        boolean[] found = new boolean[SIZE];

        int count = 1;
        for (int n = 0; n < 9; n += SIZE_SMALL) {
            for (int m = 0; m < 9; m += SIZE_SMALL) {
                for (int i = n; i < n + SIZE_SMALL; i++) {
                    for (int j = m; j < m + SIZE_SMALL; j++) {
                        if (found[sudoku[i][j] - ONE]) {
                            dataResponse.setAreaIds(count);
                            wrongSetNumber = true;
                        }
                        found[(sudoku[i][j] - ONE)] = true;
                    }
                }
                count++;
                found = new boolean[SIZE];
            }
        }

        if (wrongSetNumber) {
            return Optional.of(dataResponse);
        } else {
            return Optional.empty();
        }
    }
}
