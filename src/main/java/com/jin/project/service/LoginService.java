package com.jin.project.service;

import com.jin.project.mapper.MemberMapper;
import com.jin.project.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private MemberMapper memberMapper;

    public Map<String, Object> login(String email, String password) {
        Member member = memberMapper.findMemberByEmail(email);
        Map<String, Object> result = new HashMap<>();

        if (member != null && password.equals(member.getPassword())) {
            result.put("success", true);
            result.put("name", member.getName());  // 사용자 이름을 반환
        } else {
            result.put("success", false);
            result.put("name", null);  // 로그인 실패 시 이름은 null
        }

        return result;
    }
}
