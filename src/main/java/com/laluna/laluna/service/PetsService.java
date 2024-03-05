package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.ReadPetResponse;
import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.repository.MemberRepository;
import com.laluna.laluna.repository.PetsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetsService {

    private final PetsRepository petsRepository;
    private final MemberRepository memberRepository;

    public Pets savePet(MemberAndPetDto dto, Long mid) {
        Member member = memberRepository.findBymid(mid)
                .orElseThrow(() -> new IllegalStateException("해당 id의 회원이 존재하지 않습니다."));

        Pets pets = Pets.builder()
                .member(member)
                .petname(dto.getPetname())
                .petage(dto.getPetage())
                .petsex(dto.getPetsex())
                .petkind(dto.getPetkind())
                .petfeature(dto.getPetfeature())
                .petvac(dto.getPetvac())
                .petcondition(dto.getPetcondition())
                .build();

        System.out.println(pets);
        return petsRepository.save(pets);
    }
    
    public Member getMemberByMnum(Long mnum) {
        return memberRepository.findBymid(mnum)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다: " + mnum));
    }

    public Pets getPetByMemberAndPetId(Long mnum, Long petnum) {
        Member member = getMemberByMnum(mnum);
        return member.getPets().stream()
                .filter(pet -> pet.getPetnum().equals(petnum))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("반려동물을 찾을 수 없습니다: " + petnum));
    }
}

