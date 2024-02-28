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
    private Long petNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mNum")
    private Member member;

    private String petName;

    private int petAge;

    private Boolean petSex;

    private String petKind;

    @Column(length = 300)
    private String petPost;

    private String petVac;

    private String petCondition;

}
