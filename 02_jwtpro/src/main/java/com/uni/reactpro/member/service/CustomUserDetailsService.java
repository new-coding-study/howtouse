package com.uni.reactpro.member.service;


import com.uni.reactpro.exception.UserNotFoundException;
import com.uni.reactpro.member.dao.MemberMapper;
import com.uni.reactpro.member.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper mapper;

    public CustomUserDetailsService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        log.info("[CustomUserDetailsService] ===================================");
        log.info("[CustomUserDetailsService] loadUserByUsername {}", memberId);

        return mapper.findByMemberId(memberId)
                .map(user -> addAuthorities(user))
                .orElseThrow(() -> new UserNotFoundException(memberId + "> 찾을 수 없습니다."));
    }

    private MemberDto addAuthorities(MemberDto member) {
        member.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(member.getMemberRole())));

        return member;
    }

}