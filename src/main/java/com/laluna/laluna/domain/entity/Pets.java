package com.laluna.laluna.domain.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petNum;  //펫 이름

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mNum")
    private Member member;   //주인 정보 받아오기

    private String petName; //펫 이름

    private int petAge;  //펫 나이

    private Boolean petSex;  //펫 성별

    private String petKind;  //펫 종류

    @Column(length = 500)
    private String petFeature;  //펫 정보(특징)

    private String petVac; //펫 백신

    private String petCondition; //펫 건강상태

}
