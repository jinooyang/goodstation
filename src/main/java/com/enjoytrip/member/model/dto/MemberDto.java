package com.enjoytrip.member.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String memberId;
    private String name;
    private String password;
    private Role role;
    private String email;
    private String nickname;
}
