package com.laluna.laluna.domain.dto.join;

import com.laluna.laluna.domain.entity.Pets;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberAndPetDto {
    // 멤버 정보
    private String mid;
    private String mpw;
    private String mphone;
    private String address;
    private String email;
    private Long mnum;

    // 펫 정보
    private String petname;
    private int petage;
    private Boolean petsex;
    private String petkind;
    private String petfeature;
    private String petvac;
    private String petcondition;


}
