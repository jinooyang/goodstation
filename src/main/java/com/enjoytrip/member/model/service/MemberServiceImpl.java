package com.enjoytrip.member.model.service;

import com.enjoytrip.jwt.JwtTokenProvider;
import com.enjoytrip.member.model.dto.MemberDto;
import com.enjoytrip.member.model.dto.Role;
import com.enjoytrip.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public MemberDto findMemberById(String memberId) throws SQLException {

        return memberMapper.findMemberById(memberId);
    }

    @Override
    public void registerMember(MemberDto member) throws Exception {


        memberMapper.registerMember(member);
    }

    @Override
    public int findMemberByEmail(String email) throws Exception {
        return memberMapper.findMemberByEmail(email);
    }

    @Override
    public int findMemberByNickname(String nickname) throws Exception {
        return memberMapper.findMemberByNickname(nickname);
    }

}
