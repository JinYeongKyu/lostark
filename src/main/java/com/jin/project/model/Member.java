package com.jin.project.model;

import lombok.Data;
import java.util.Date;

@Data
public class Member {
    private Long id;              // 자동 증가하는 ID
    private String email;         // 이메일 (고유)
    private String password;      // 비밀번호
    private String name;          // 이름
    private String role;          // 역할
    private Date createdAt;       // 생성일
}
