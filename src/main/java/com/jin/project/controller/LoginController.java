package com.jin.project.controller;

import com.jin.project.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest, HttpSession session) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        // loginService에서 사용자 정보와 성공 여부를 반환
        Map<String, Object> loginResult = loginService.login(email, password);

        boolean success = (boolean) loginResult.get("success");
        String name = (String) loginResult.get("name");

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        if (success) {
            // 로그인 성공 시, 세션에 사용자 정보 저장
            session.setAttribute("user", email);
            session.setAttribute("name", name);  // 세션에 사용자 이름 저장
            response.put("message", "로그인 성공");
        } else {
            response.put("message", "이메일 또는 비밀번호가 잘못되었습니다.");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-login")
    public ResponseEntity<Map<String, Object>> checkLogin(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        String user = (String) session.getAttribute("user");
        String name = (String) session.getAttribute("name");

        if (user != null) {
            response.put("loggedIn", true);
            response.put("user", user);
            response.put("name", name);
        } else {
            response.put("loggedIn", false);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        session.invalidate();  // 세션 정보 무효화 (로그아웃)
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "로그아웃 성공");
        return ResponseEntity.ok(response);
    }
}
