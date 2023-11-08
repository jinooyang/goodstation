package com.enjoytrip.member.controller;

import com.enjoytrip.jwt.JwtTokenProvider;
import com.enjoytrip.member.model.dto.MemberDto;
import com.enjoytrip.member.model.mapper.MemberMapper;
import com.enjoytrip.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> user)throws SQLException {
        MemberDto member = memberService.findMemberById(user.get("id"));
        if(!passwordEncoder.matches(user.get("password"), member.getPassword())){
            throw new IllegalArgumentException("이메일 또는 비밀번호가 맞지 않습니다.");
        }
        return jwtTokenProvider.createToken(member.getMemberId(), member.getRole());
    }


}
