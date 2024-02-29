package com.laluna.laluna.domain.dto.member;

import com.laluna.laluna.domain.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadMemberResponse {

    private Long mNum;

    private String mId;

    private String mPw;

    private String mPhone;

    private Boolean mSex;

    private String address;

    private String email;

    private Photo photo;

}
