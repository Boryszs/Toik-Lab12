package com.demo.controller;


import com.demo.dto.DataResponse;
import com.demo.service.SudokuCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/sudoku")
public class SudokuController {


    private SudokuCheck sudokuCheck;

    @Autowired
    public SudokuController(SudokuCheck sudokuCheck) {
        this.sudokuCheck = sudokuCheck;
    }

    @GetMapping("/verify")
    public ResponseEntity<?> getBoard() {
        Optional<DataResponse> data = sudokuCheck.checkBoard();
        if (!data.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(data.get(), HttpStatus.BAD_REQUEST);
    }
}