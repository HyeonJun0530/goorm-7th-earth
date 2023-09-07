package com.example.controller;

import com.example.dto.member.JoinMemberDto;
import com.example.dto.member.LoginMemberDto;
import com.example.exception.member.LoginMemberException;
import com.example.service.MemberService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody JoinMemberDto joinMemberDto) {
        memberService.join(joinMemberDto);

        return new ResponseEntity("성공적으로 가입이 완료되었습니다.", OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginMemberDto.class))),
            @ApiResponse(responseCode = "404", description = "로그인 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginMemberException.class))),
    })
    @GetMapping("/login")
    public ResponseEntity login(@RequestBody LoginMemberDto loginMemberDto) {
        JoinMemberDto login = memberService.login(loginMemberDto);

        return new ResponseEntity(login, OK);
    }
}
