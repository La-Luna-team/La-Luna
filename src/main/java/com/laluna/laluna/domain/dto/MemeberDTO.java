package com.laluna.laluna.domain.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemeberDTO {

    private Long mNum;
    private Long mId;
    private String mPw;
    private String mPnum;
    private Boolean mSex;
    private String address;
    private String email;

}
