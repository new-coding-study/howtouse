package com.uni.reactpro.member.service;

import com.uni.reactpro.member.dao.MemberMapper;
import com.uni.reactpro.member.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Slf4j
@Service
public class MemberService {
    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @GetMapping
    public MemberDto selectMyInfo(@PathVariable String memberId) {
        log.info("[MemberService] getMyInfo Start ==============================");

        MemberDto member = memberMapper.selectByMemberId(memberId);
        log.info("[MemberService] {}", member);
        log.info("[MemberService] getMyInfo End ==============================");

        return member;
    }
}