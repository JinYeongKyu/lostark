package com.jin.project.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private Long boardNo;
    private String boardTitle;
    private String boardContent;
    private String name;
    private Long boardMember;
    private LocalDateTime boardCreatedAt;
    private LocalDateTime boardUpdatedAt;
}
