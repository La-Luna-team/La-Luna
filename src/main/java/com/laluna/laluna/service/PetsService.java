package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.board.UpdateBoardResponse;
import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.UpdatePetRequest;
import com.laluna.laluna.domain.dto.pet.UpdatePetResponse;
import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.repository.MemberRepository;
import com.laluna.laluna.repository.PetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Pets> findByMember(Long mnum){
        return petsRepository.findByMember_mnum(mnum);
    }

    @Transactional
    public UpdatePetResponse petUpdate(Long mnum, UpdatePetRequest dto) {
        List<Pets> findMember = petsRepository.findByMember_mnum(mnum);

        for (Pets pet : findMember) {
            pet.update(dto.getPetName(), dto.getPetAge(), dto.getPetSex(), dto.getPetKind(), dto.getPetFeature(), dto.getPetVac(), dto.getPetCondition());
        }

        Pets updatedPet = findMember.get(0);

        return new UpdatePetResponse(
                updatedPet.getPetnum(),
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
}