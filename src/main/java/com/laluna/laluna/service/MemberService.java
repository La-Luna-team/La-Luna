package com.laluna.laluna.service;

import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public Optional<Member> findOne(String mid) {
        return repository.findBymid(mid);
    }

    public boolean isValidMember(String mid, String password) {
        Optional<Member> member = findOne(mid);
        if(member.isPresent()) {
            return member.get().getMpw().equals(password);
        }
        return false;
    }
}
