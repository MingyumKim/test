package com.test.test.controller;

import com.test.test.entity.Member;
import com.test.test.param.MemberParam;
import com.test.test.repository.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
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
    @PostMapping("/members/create")
    @Operation(summary = "createMember", description = "create Member API")
    public ResponseEntity<Long> memberCreate(@RequestBody MemberParam param) {

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
    @GetMapping("/member/{no}")
    public ResponseEntity<Member> memberShow(@PathVariable Long no) {

        Optional<Member> member = memberRepository.findById(no);

        if (member.isPresent()) {
            return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Member>(member.get(), HttpStatus.NOT_FOUND);
        }

    }
}
