package com.enjoytrip.member.model.service;

import com.enjoytrip.jwt.JwtTokenProvider;
import com.enjoytrip.member.model.dto.MemberDto;
import com.enjoytrip.member.model.dto.Role;
import com.enjoytrip.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    @Override
    public MemberDto findMemberById(String memberId) throws SQLException {

        return memberMapper.findMemberById(memberId);
    }

    @Override
    public void registerMember(MemberDto member) throws Exception {


        memberMapper.registerMember(member);
    }
}
