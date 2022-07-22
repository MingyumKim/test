package com.test.test.controller;

import com.test.test.entity.Member;
import com.test.test.param.MemberParam;
import com.test.test.repository.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    //생성자 주입
    private final MemberRepository memberRepository;

    /**
     * 멤버 생성
     * @param param
     * @return
     */
    @Operation(summary = "멤버 생성", description = "멤버 생성 (ID, Name, Password)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK ! !"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST ! !"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND ! !"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR ! !")
    })
    @PostMapping("/members/create")
    public ResponseEntity<Long> memberCreate(@Parameter(description = "멤버정보", required = true, example = "id : test, name : test, pw : 1234") @RequestBody MemberParam param) {

        log.info(param.toString());

        Member member = new Member();
        member.setId(param.getId());
        member.setName(param.getName());
        member.setPw(param.getPw());
        member.prePersist();

        memberRepository.save(member);

        return new ResponseEntity<Long>(member.getNo(), HttpStatus.CREATED);
    }

    /**
     * 멤버번호로 멤버 확인
     * @param no
     * @return
     */
    @Operation(summary = "멤버 조회", description = "번호로 멤버 조회 (No)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK ! !"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST ! !"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND ! !"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR ! !")
    })
    @GetMapping("/member/{no}")
    public ResponseEntity<Member> memberShow(@Parameter(description = "번호로 멤버 조회", required = true, example = "1") @PathVariable Long no) {

        Optional<Member> member = memberRepository.findById(no);

        if (member.isPresent()) {
            return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Member>(member.get(), HttpStatus.NOT_FOUND);
        }

    }
}
