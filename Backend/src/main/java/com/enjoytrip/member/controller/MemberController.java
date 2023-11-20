package com.enjoytrip.member.controller;


import com.enjoytrip.jwt.JwtTokenProvider;
import com.enjoytrip.member.model.dto.MemberDto;
import com.enjoytrip.member.model.dto.Role;
import com.enjoytrip.member.model.mapper.MemberMapper;
import com.enjoytrip.member.model.service.MemberService;
import com.enjoytrip.response.ResponseMessage;
import com.enjoytrip.response.StatusEnum;
import com.enjoytrip.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        MemberDto member = memberService.findMemberById(user.get("memberId"));

        if (member == null || !passwordEncoder.matches(user.get("password"), member.getPassword())) {
            rm.setMessage("로그인 실패. 아이디 혹은 비밀번호를 확인해주세요");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.UNAUTHORIZED);
        } else {
            rm.setMessage("로그인 성공");
            rm.setStatus(StatusEnum.OK);
            rm.setData("token", jwtTokenProvider.createToken(member.getMemberId(), member.getRole()));
            return new ResponseEntity<>(rm, HttpStatus.CREATED);
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

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseMessage> memberEmailDupCheck(@PathVariable String email) {
        ResponseMessage rm = new ResponseMessage();
        try {
            int mem = memberService.findMemberByEmail(email);
            if (mem > 0) {
                rm.setMessage("사용 불가능한 이메일 입니다.");
                rm.setData("pos", 0);
                rm.setStatus(StatusEnum.OK);
                return new ResponseEntity<>(rm, HttpStatus.OK);
            }
            rm.setMessage("사용가능한 이메일 입니다.");
            rm.setData("pos", 1);
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            rm.setMessage("사용 불가능한 이메일 입니다.");
            rm.setData("pos", 0);
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/memberId/{memberId}")
    public ResponseEntity<ResponseMessage> memberIdDupCheck(@PathVariable String memberId) {
        ResponseMessage rm = new ResponseMessage();
        try {
            MemberDto mem = memberService.findMemberById(memberId);
            if (mem != null) {
                rm.setMessage("사용 불가능한 아이디 입니다.");
                rm.setData("pos", 0);
                rm.setStatus(StatusEnum.OK);
                return new ResponseEntity<>(rm, HttpStatus.OK);
            }
            rm.setMessage("사용가능한 아이디 입니다.");
            rm.setData("pos", 1);
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            rm.setMessage("사용 불가능한 아이디 입니다.");
            rm.setData("pos", 0);
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<ResponseMessage> memberNickNameDupCheck(@PathVariable String nickname) {
        ResponseMessage rm = new ResponseMessage();
        try {
            int mem = memberService.findMemberByNickname(nickname);
            if (mem > 0) {
                rm.setMessage("사용 불가능한 닉네임 입니다.");
                rm.setData("pos", 0);
                rm.setStatus(StatusEnum.OK);
                return new ResponseEntity<>(rm, HttpStatus.OK);
            }
            rm.setMessage("사용가능한 닉네임 입니다.");
            rm.setData("pos", 1);
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            rm.setMessage("사용 불가능한 닉네임 입니다.");
            rm.setData("pos", 0);
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/info")
    public ResponseEntity<ResponseMessage> updateMemberInfo(@RequestBody Map<String, String> map, @AuthenticationPrincipal SecurityUser securityUser) {

        ResponseMessage rm = new ResponseMessage();
        try {
            if (map.get("password") != null)
                map.put("password", passwordEncoder.encode(map.get("password")));
            map.put("memberId", securityUser.getUsername());
            memberService.updateMemberInfo(map);
            MemberDto member = memberService.findMemberById(map.get("memberId"));
            rm.setStatus(StatusEnum.OK);
            rm.setMessage("회원 정보 수정 성공");
            rm.setData("X-AUTH-TOKEN", jwtTokenProvider.createToken(member.getMemberId(), member.getRole()));
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setMessage("회원 정보 수정 실패");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/info")
    public ResponseEntity<ResponseMessage> getMemberInfo(@AuthenticationPrincipal SecurityUser securityUser) {
//        System.out.println(securityUser.getMember().toString());
        ResponseMessage rm = new ResponseMessage();
        try {
            MemberDto member = memberService.findMemberById(securityUser.getUsername());
            rm.setStatus(StatusEnum.OK);
            rm.setMessage("회원 정보 조회 성공");
            rm.setData("memberInfo", member);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setStatus(StatusEnum.FAIL);
            rm.setMessage("회원 정보 조회 실패");
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }


}
