package com.demo.mapper;

import java.util.List;
import java.util.Map;

public interface SudokuMapper {

    int[][] convertToSudokuBoard(List<String> lines);
}
