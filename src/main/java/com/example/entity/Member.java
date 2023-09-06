package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "MEMBER")
@NoArgsConstructor
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_idx")
    private String memberIdx;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_phone_number")
    private String phoneNumber;

}
