package com.enjoytrip.member.model.mapper;

import com.enjoytrip.member.model.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface MemberMapper {
    MemberDto findMemberById (String memberId) throws SQLException;
    void registerMember(String memberId) throws SQLException;

}
