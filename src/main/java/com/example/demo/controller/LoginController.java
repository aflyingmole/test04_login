package com.example.demo.controller;

import com.example.demo.dto.Login;
import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Controller
public class LoginController {
    @Autowired
    private MemberService service;

    @GetMapping("/member/login")
    public String loginForm() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(Login login, HttpServletRequest req) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", login.getId());
        map.put("pwd", login.getPwd());
        Member m = service.isMember(map);
        if (m == null) {
            return "member/login";
        } else {
            HttpSession session = req.getSession(); // 세션객체 얻어오기
            session.setAttribute("id", m.getId());// 세션에 아이디 저장
        }
        return "redirect:/";
    }
}











