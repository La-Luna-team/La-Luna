package com.laluna.laluna.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberResponse {

    private Long mNum;

    private String mId;

    private String mPw;

    private String mPhone;

    private Boolean mSex;

    private String address;

    private String email;

}
