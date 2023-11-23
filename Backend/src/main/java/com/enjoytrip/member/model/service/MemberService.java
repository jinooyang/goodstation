package com.enjoytrip.member.model.service;

import com.enjoytrip.member.model.dto.MemberDto;
import com.enjoytrip.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.Map;


public interface MemberService {
    MemberDto findMemberById(String memberId) throws SQLException;

    void registerMember(MemberDto memberId) throws Exception;

    int findMemberByEmail(String email) throws Exception;

    int findMemberByNickname(String nickname) throws Exception;
    void updateMemberInfo(Map<String,String> map) throws Exception;

    void updateMemberNameNickEmail(Map<String,String> map) throws Exception;

    void updateMemberPassword(Map<String,String> map) throws Exception;

}
