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
                for (int n = j + 1; n < SIZE; n++) {
                    if (sudoku[i][j] != 0 && sudoku[i][j] == sudoku[i][n]) {
                        dataResponse.setLineIds(i + ONE);
                        wrongSetNumber = true;
                    }
                    if (sudoku[j][i] != 0 && sudoku[j][i] == sudoku[n][i]) {
                        dataResponse.setKolumnIds(i + ONE);
                        wrongSetNumber = true;
                    }
                }
            }
        }


        boolean[] found = new boolean[9];

        int count = 1;
        for (int n = 0; n < 9; n += 3) {
            for (int m = 0; m < 9; m += 3) {
                for (int i = n; i < n + 3; i++) {
                    for (int j = m; j < m + 3; j++) {
                        if (found[sudoku[i][j] - 1]) {
                            dataResponse.setAreaIds(count);
                            wrongSetNumber = true;
                        }
                        found[(sudoku[i][j] - 1)] = true;
                    }
                }
                count++;
                found = new boolean[9];
            }
        }

        if (wrongSetNumber) {
            return Optional.of(dataResponse);
        } else {
            return Optional.empty();
        }
    }
}
