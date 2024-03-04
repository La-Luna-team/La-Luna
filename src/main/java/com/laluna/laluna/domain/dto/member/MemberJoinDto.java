package com.laluna.laluna.domain.dto.member;

import com.laluna.laluna.domain.entity.Photo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDto {

    private String mid;

    private String mpw;

    private String mphone;

    private String address;

    private String email;

    @Override
    public String toString() {
        return "MemberJoinDto{" +
                "mid='" + mid + '\'' +
                ", mpw='" + mpw + '\'' +
                ", mphone='" + mphone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

