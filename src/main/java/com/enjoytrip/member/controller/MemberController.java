package com.enjoytrip.member.controller;


import com.enjoytrip.jwt.JwtTokenProvider;
import com.enjoytrip.member.model.dto.MemberDto;
import com.enjoytrip.member.model.dto.Role;
import com.enjoytrip.member.model.mapper.MemberMapper;
import com.enjoytrip.member.model.service.MemberService;
import com.enjoytrip.response.ResponseMessage;
import com.enjoytrip.response.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody Map<String, String> user) throws Exception {
        ResponseMessage rm = new ResponseMessage();
        MemberDto member = memberService.findMemberById(user.get("id"));

        if (member == null || !passwordEncoder.matches(user.get("password"), member.getPassword())) {
            rm.setMessage("로그인 실패. 아이디 혹은 비밀번호를 확인해주세요");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.UNAUTHORIZED);
        } else {
            rm.setMessage("로그인 성공");
            rm.setStatus(StatusEnum.OK);
            rm.setData("token", jwtTokenProvider.createToken(member.getMemberId(), member.getRole()));
            return new ResponseEntity<>(rm, HttpStatus.OK);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> signUp(@RequestBody MemberDto member) {
        ResponseMessage rm = new ResponseMessage();
        try {
            member.setRole(Role.USER);
            member.setPassword(passwordEncoder.encode(member.getPassword()));

            memberService.registerMember(member);
            member = memberService.findMemberById(member.getMemberId());
            rm.setStatus(StatusEnum.OK);
            rm.setData("memberId", member.getMemberId());
            rm.setData("name", member.getName());
            rm.setData("email", member.getEmail());
            rm.setMessage("회원가입 성공");
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            rm.setMessage("회원가입 실패");
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/email")
    public ResponseEntity<ResponseMessage> emailDupCheck(@RequestBody Map<String, String> map) throws Exception {
        ResponseMessage rm = new ResponseMessage();
        int dup = memberService.findMemberByEmail(map.get("email"));
        if(dup==0){
            rm.setMessage("이메일 사용 가능");
            rm.setStatus(StatusEnum.OK);
        }
        else{
            rm.setMessage("이미 사용중인 이메일입니다");
            rm.setStatus(StatusEnum.FAIL);
        }
        return new ResponseEntity<>(rm,HttpStatus.OK);

    }

    @GetMapping("/nickname")
    public ResponseEntity<ResponseMessage>  nicknameDupCheck(@RequestBody Map<String, String> map) throws Exception {

        ResponseMessage rm = new ResponseMessage();
        int dup = memberService.findMemberByNickname(map.get("nickname"));
        if(dup==0){
            rm.setMessage("닉네임 사용 가능");
            rm.setStatus(StatusEnum.OK);
        }
        else{
            rm.setMessage("이미 사용중인 닉네임입니다");
            rm.setStatus(StatusEnum.FAIL);
        }
        return new ResponseEntity<>(rm,HttpStatus.OK);
    }

}
