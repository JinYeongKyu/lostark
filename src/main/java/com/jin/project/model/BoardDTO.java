package com.jin.project.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private Long boardNo;          // 게시글 번호
    private String boardTitle;     // 게시글 제목
    private String boardContent;
    private String name;// 게시글 내용
    private Long boardMember;      // 작성자 ID
    private LocalDateTime boardCreatedAt; // 생성일
    private LocalDateTime boardUpdatedAt; // 수정일
}
