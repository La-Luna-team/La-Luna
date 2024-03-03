package com.laluna.laluna.config;

import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.service.MemberService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    public MyUserDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        Optional<Member> findOne = memberService.findOne(insertedUserId);
        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

        return User.builder()
                .username(member.getMid())
                .password(member.getMpw())
                .roles(member.getRoles())
                .build();
    }
}
