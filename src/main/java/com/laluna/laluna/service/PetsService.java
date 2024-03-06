package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.CreatePetRequest;
import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.repository.MemberRepository;
import com.laluna.laluna.repository.PetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetsService {

    private final PetsRepository petsRepository;
    private final MemberRepository memberRepository;

    public Pets savePet(MemberAndPetDto dto, String mid) {
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

    public List<Pets> findPetsByMemberMid(Long mnum) {
        return petsRepository.findByMember_Mnum(mnum);
    }
}
