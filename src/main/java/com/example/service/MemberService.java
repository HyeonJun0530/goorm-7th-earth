package com.example.service;

import com.example.dto.member.JoinMemberDto;
import com.example.dto.member.LoginMemberDto;
import com.example.entity.Member;
import com.example.exception.global.exception.ResourceNotFoundException;
import com.example.exception.member.LoginMemberException;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.exception.global.GlobalErrorCode.RESOURCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(JoinMemberDto joinMemberDto) {
        Member member = Member.createMember(joinMemberDto.getMemberIdx(), joinMemberDto.getPassword(),
                joinMemberDto.getNickname(), joinMemberDto.getPhoneNumber());

        memberRepository.save(member);
    }

    public JoinMemberDto login(LoginMemberDto loginMemberDto) {
        Member member = memberRepository.findMemberByMemberIdx(loginMemberDto.getMemberIdx())
                .orElseThrow(() -> new LoginMemberException());

        return new JoinMemberDto().builder().memberIdx(member.getMemberIdx()).nickname(member.getNickname())
                .phoneNumber(member.getPhoneNumber()).build();
    }

    public Member getMember(String idx) {
        return memberRepository.findMemberByMemberIdx(idx)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
    }
}
