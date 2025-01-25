package com.jin.project.mapper;

import com.jin.project.model.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> getAllBoard();

    BoardDTO getSelectBoard(Long id);

    void deleteBoard(Long id);
}
