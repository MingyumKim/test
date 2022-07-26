package com.test.test.service;

import com.test.test.entity.Member;
import com.test.test.param.MemberParam;
import com.test.test.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long createMember(MemberParam param) {

        Member member = new Member();

        member.setId(param.getId());
        member.setName(param.getName());
        member.setPw(param.getPw());
        member.prePersist();

        memberRepository.save(member);

        return member.getNo();
    }


    public Optional<Member> detailMember(Long no) {
        return memberRepository.findById(no);
    }

    public void deleteMember(Long no) {
        memberRepository.deleteById(no);
    }
}
