package com.example.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginMemberDto {

    private String memberIdx;

    private String password;

}
