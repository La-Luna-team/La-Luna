package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.member.MemberUpdateRequestDto;
import com.laluna.laluna.domain.dto.member.MemberUpdateResponseDto;
import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterMemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository repository;

    public Member join(String memberid, String memberpassword, String phone, String address,
                     String email, Long memberno){
        Member member = Member.createUser(memberid, memberpassword, passwordEncoder, phone, address, email, memberno);
        validateDuplicateMember(member);

        return repository.save(member);


    }

    private void validateDuplicateMember(Member member) {
        repository.findBymemberid(member.getMemberid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    public void deleteMember(String memberid) {
        Member member = repository.findBymemberid(memberid)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));

        repository.delete(member);
    }

    @Transactional
    public MemberUpdateResponseDto memberUpdate(String memberid, MemberUpdateRequestDto requestDto) {
        Member member = repository.findBymemberid(memberid)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));

        // PasswordEncoder를 사용하여 비밀번호를 암호화하여 업데이트
        String encodedPassword = passwordEncoder.encode(requestDto.getMemberpassword());

        member.memberUpdate(
                requestDto.getMemberid(),
                encodedPassword, // 이미 암호화된 비밀번호 사용
                requestDto.getPhone(),
                requestDto.getAddress(),
                requestDto.getEmail());

        return new MemberUpdateResponseDto(
                member.getMemberno(),
                member.getMemberid(),
                member.getPhone(),
                member.getAddress(),
                member.getEmail());
    }


}
