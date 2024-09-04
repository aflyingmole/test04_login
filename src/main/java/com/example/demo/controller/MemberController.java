package com.example.demo.controller;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberService service;

    @GetMapping("/member/join")
    public String join(@ModelAttribute Member member) {
        return "member/join";
    }

    @PostMapping("/member/join")
    public String insert(@ModelAttribute Member member, BindingResult bindingResult, Model model) {
        if (!StringUtils.hasText(member.getId())) {
            //bindingResult에 데러담기("객체명", "필드명", "에러메시지")
            bindingResult.addError(new FieldError("member", "id", "아이디를 입력하세요"));
        }
        if (!StringUtils.hasText(member.getPwd())) {
            bindingResult.addError(new FieldError("member", "pwd", "비밀번호를 입력하세요"));
        }
        if (member.getAge() > 150 || member.getAge() < 0) {
            bindingResult.addError(new FieldError("member", "age", "0에서 150사이의 나이를 입력하세요"));
        }
        if(bindingResult.hasErrors()) {
            return "member/join";
        }
        try {
            service.insert(member);
            model.addAttribute("result", "join complete😍😍😍");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("result", "join failed😢😢😢");
        }
        return "member/result";
    }
}
