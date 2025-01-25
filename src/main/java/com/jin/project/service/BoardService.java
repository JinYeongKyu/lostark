package com.jin.project.service;

import com.jin.project.mapper.BoardMapper;
import com.jin.project.model.BoardDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    public BoardMapper boardMapper;

    @Autowired
    private HttpSession session; // HttpSession 주입

    public List<BoardDTO> getAllBoard() {
        return boardMapper.getAllBoard();
    }

    public BoardDTO getSelectBoard(Long id) {
        return boardMapper.getSelectBoard(id);
    }

    public void deleteBoard(Long id) {
        boardMapper.deleteBoard(id);
    }

    public void createPost(BoardDTO boardDTO) {
        String name = (String) session.getAttribute("name");
        boardDTO.setName(name);
        boardMapper.createPost(boardDTO);
    }
}
