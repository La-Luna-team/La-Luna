package com.laluna.laluna.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memeber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mNum;

    @Column(length = 20, nullable = false)
    private Long mId;

    @Column(length = 20, nullable = false)
    private String mPw;
    private String mPnum;
    private Boolean mSex;
    private String address;
    private String email;
}
