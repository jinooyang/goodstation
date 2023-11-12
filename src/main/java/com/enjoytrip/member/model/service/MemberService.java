package com.enjoytrip.member.model.service;

import com.enjoytrip.member.model.dto.MemberDto;
import com.enjoytrip.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;


public interface MemberService {
    MemberDto findMemberById (String memberId) throws SQLException;
    void registerMember(MemberDto memberId) throws Exception;

}
