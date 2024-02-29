package com.laluna.laluna.domain.dto.pet;

import com.laluna.laluna.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePetRequest {

    private Member member;   //주인 정보 받아오기

    private String petName; //펫 이름

    private int petAge;  //펫 나이

    private Boolean petSex;  //펫 성별

    private String petKind;  //펫 종류

    private String petFeature;  //펫 정보(특징)

    private String petVac; //펫 백신

    private String petCondition;

}
