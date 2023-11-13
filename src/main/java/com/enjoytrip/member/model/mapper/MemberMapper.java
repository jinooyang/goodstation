package com.enjoytrip.member.model.mapper;

import com.enjoytrip.member.model.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface MemberMapper {
    MemberDto findMemberById (String memberId) throws SQLException;
    void registerMember(MemberDto memberId) throws SQLException;
     int findMemberByEmail(String email) throws Exception ;

    int findMemberByNickname(String nickname) throws Exception;
}
