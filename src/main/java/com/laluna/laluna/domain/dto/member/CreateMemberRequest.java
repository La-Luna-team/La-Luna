package com.laluna.laluna.domain.dto.member;

import com.laluna.laluna.domain.entity.Photo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberRequest {

    private String mid;

    private String mpw;

    private String mphone;

    private String address;

    private String email;


}
