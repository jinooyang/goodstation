package com.enjoytrip.security;


import com.enjoytrip.member.model.dto.MemberDto;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    private MemberDto member;

    public SecurityUser(MemberDto member) {
        super(member.getMemberId(), member.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_"+member.getRole().name()));
        this.member = member;
    }

    public MemberDto getMember() {
        return member;
    }
}