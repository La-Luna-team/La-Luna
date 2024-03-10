package com.laluna.laluna.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDto {

    private String memberid;

    private String memberpassword;

    private String phone;

    private String address;

    private String email;

}
