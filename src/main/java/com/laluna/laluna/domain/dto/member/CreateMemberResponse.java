package com.laluna.laluna.domain.dto.member;

import com.laluna.laluna.domain.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberResponse {

    private Long mnum;

    private String mid;

    private String mpw;

    private String mphone;

    private String address;

    private String email;

}
