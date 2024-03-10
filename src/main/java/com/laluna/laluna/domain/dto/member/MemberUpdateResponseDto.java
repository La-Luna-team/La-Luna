package com.laluna.laluna.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateResponseDto {

    private Long memberno;

    private String memberid;

    private String phone;

    private String address;

    private String email;
}
