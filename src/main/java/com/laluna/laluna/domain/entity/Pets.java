package com.laluna.laluna.domain.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private Member member;   //주인 아이디 받아오기

    private String petname; //펫 이름

    private int petage;  //펫 나이

    private Boolean petsex;  //펫 성별

    private String petkind;  //펫 종류

    @Column(length = 500)
    private String petfeature;  //펫 정보(특징)

    private String petvac; //펫 백신

    private String petcondition; //펫 건강상태


    public static Pets createPet( Member member, String petname, int petage, Boolean petsex,
                                   String petkind, String petfeature,String petvac, String petcondition ) {

        return Pets.builder()
                .member(member)
                .petname(petname)
                .petage(petage)
                .petsex(petsex)
                .petkind(petkind)
                .petfeature(petfeature)
                .petvac(petvac)
                .petcondition(petcondition)
                .build();
    }

}
