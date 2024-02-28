package com.laluna.laluna.domain.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @GeneratedValue
    private Long petNum;

    private Long mNum;
    private String petPost;
    private Long petSex;
    private String petAge;
    private String petKind;
    private String petVac;
    private String petCondition;
    private String petImage;
    private String petName;
}
