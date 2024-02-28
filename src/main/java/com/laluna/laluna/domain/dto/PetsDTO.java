package com.laluna.laluna.domain.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PetsDTO {

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
