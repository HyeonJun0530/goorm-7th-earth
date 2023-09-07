package com.example.repository;

import com.example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByMemberIdx(String memberIdx);
    Optional<Member> findMemberByNickname(String nickname);
}