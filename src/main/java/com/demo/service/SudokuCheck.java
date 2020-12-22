package com.demo.service;


import com.demo.dto.DataResponse;

import java.util.Optional;

public interface SudokuCheck {
    Optional<DataResponse> checkBoard();
}
