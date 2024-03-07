package com.laluna.laluna.service;

import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public Optional<Member> findOne(String memberid) {
        return repository.findBymemberid(memberid);
    }

    public boolean isValidMember(String memberid, String password) {
        Optional<Member> member = findOne(memberid);
        if(member.isPresent()) {
            return member.get().getMemberpassword().equals(password);
        }
        return false;
    }

}
