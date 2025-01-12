package com.jin.project.controller;

import com.jin.project.model.BoardDTO;
import com.jin.project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public List<BoardDTO> getAllBoard() {
        return boardService.getAllBoard();
    }
}
