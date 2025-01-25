package com.jin.project.controller;

import com.jin.project.model.BoardDTO;
import com.jin.project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/board/detail/{id}")
    public BoardDTO getSelectBoard(@PathVariable("id") Long id) {
        return boardService.getSelectBoard(id);
    }

    @DeleteMapping("/board/delete/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
    }
}
