package com.jin.project.service;

import com.jin.project.mapper.BoardMapper;
import com.jin.project.model.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    public BoardMapper boardMapper;

    public List<BoardDTO> getAllBoard() {
        return boardMapper.getAllBoard();
    }
}
