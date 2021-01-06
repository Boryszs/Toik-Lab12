package com.demo.mapper;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SudokuMapperImpl implements SudokuMapper {

    @Override
    public int[][] convertToSudokuBoard(List<String> lines) {

        int[][] sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String[] row = lines.get(i).split(";");
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(row[j]);
            }
        }

        return sudoku;
    }
}
