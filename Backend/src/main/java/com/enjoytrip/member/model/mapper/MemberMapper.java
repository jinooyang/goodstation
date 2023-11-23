package com.enjoytrip.member.model.mapper;

import com.enjoytrip.member.model.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface MemberMapper {
    MemberDto findMemberById (String memberId) throws SQLException;
    void registerMember(MemberDto memberId) throws SQLException;
     int findMemberByEmail(String email) throws Exception ;

    int findMemberByNickname(String nickname) throws Exception;

    void updateMemberInfo(MemberDto member) throws Exception;

    void updateMemberNameNickEmail(Map<String,String> map) throws Exception;

    void updateMemberPassword(Map<String,String> map) throws Exception;
}
