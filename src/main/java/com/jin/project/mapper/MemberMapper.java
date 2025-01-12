package com.jin.project.mapper;

import com.jin.project.model.BoardDTO;
import com.jin.project.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    Member findMemberByEmail(String email);



}
