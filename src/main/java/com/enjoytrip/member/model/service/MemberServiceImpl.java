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

    @Override
    public void updateMemberInfo(Map<String, String> map) throws Exception {
        MemberDto member = memberMapper.findMemberById(map.get("memberId"));
        if (map.get("password") != null && map.get("password").length()>0)
            member.setPassword(map.get("password"));
        if (map.get("name") != null && map.get("name").length()>0)
            member.setName(map.get("name"));
        if (map.get("nickname") != null && map.get("nickname").length()>0)
            member.setNickname(map.get("nickname"));
        memberMapper.updateMemberInfo(member);
    }

}
