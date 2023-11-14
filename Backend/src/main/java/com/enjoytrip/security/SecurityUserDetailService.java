package com.enjoytrip.security;


import com.enjoytrip.member.model.dto.MemberDto;
import com.enjoytrip.member.model.mapper.MemberMapper;
import com.enjoytrip.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
//@Primary
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {

    private final MemberService memberService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("loadByUserName");

        MemberDto member = null;
        try {
            member = memberService.findMemberById(username);
            return new SecurityUser(member);
        } catch (SQLException e) {
            throw new UsernameNotFoundException(username + " 사용자 없음");
        }

    }
}