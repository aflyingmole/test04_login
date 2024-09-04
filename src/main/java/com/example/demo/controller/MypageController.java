package com.example.demo.controller;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {
    @Autowired
    private MemberService service;

    @GetMapping("/member/mypage")
    public String mypage(HttpServletRequest req, Member member, Model model) {
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");
        Member m = service.select(id);
        System.out.println(id);
        model.addAttribute("member", m);
        return "member/mypage";
    }
}
