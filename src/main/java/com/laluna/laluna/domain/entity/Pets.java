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
    private Long petnum;  //펫 번호

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mnum")
    private Member member;   //주인 정보 받아오기

    private String petname; //펫 이름

    private int petage;  //펫 나이

    private Boolean petsex;  //펫 성별

    private String petkind;  //펫 종류

    @Column(length = 500)
    private String petfeature;  //펫 정보(특징)

    private String petvac; //펫 백신

    private String petcondition; //펫 건강상태

}
