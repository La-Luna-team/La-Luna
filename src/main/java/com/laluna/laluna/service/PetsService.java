package com.laluna.laluna.service;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.UpdatePetRequest;
import com.laluna.laluna.domain.dto.pet.UpdatePetResponse;
import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.repository.MemberRepository;
import com.laluna.laluna.repository.PetsRepository;
import groovy.util.logging.Slf4j;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PetsService {

    private final PetsRepository petsRepository;
    private final MemberRepository memberRepository;

    public Pets savePet(MemberAndPetDto dto, String memberid) {
        Member member = memberRepository.findBymemberid(memberid)
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

    public Pets addPet(MemberAndPetDto dto, String username) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member member = memberRepository.findBymemberid(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("해당 유저의 회원 정보가 존재하지 않습니다."));

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

        return petsRepository.save(pets);
    }


    public List<Pets> findByMember(Long memberno){
        return petsRepository.findByMember_memberno(memberno);
    }

    @Transactional
    public UpdatePetResponse petUpdate(Long memberno, UpdatePetRequest dto) {
        List<Pets> findMember = petsRepository.findByMember_memberno(memberno);

        for (Pets pet : findMember) {
            pet.update(dto.getPetname(), dto.getPetage(), dto.getPetsex(), dto.getPetkind(), dto.getPetfeature(), dto.getPetvac(), dto.getPetcondition());
        }

        Pets updatedPet = findMember.get(0);

        return new UpdatePetResponse(
                updatedPet.getPetno(),
                updatedPet.getMember(),
                updatedPet.getPetname(),
                updatedPet.getPetage(),
                updatedPet.getPetsex(),
                updatedPet.getPetkind(),
                updatedPet.getPetfeature(),
                updatedPet.getPetvac(),
                updatedPet.getPetcondition()
        );
    }

    @Transactional
    public void deletePet(String memberid, Long petno) {
        Member member = memberRepository.findBymemberid(memberid)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 회원이 존재하지 않습니다."));

        Pets pet = petsRepository.findByMemberAndPetno(member, petno)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 회원에게 해당하는 펫이 존재하지 않습니다."));

        petsRepository.delete(pet);
    }
}