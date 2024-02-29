package com.laluna.laluna.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mNum;

    @Column(length = 20, nullable = false)
    private String mId;

    @Column(length = 20, nullable = false)
    private String mPw;

    private String mPhone;

    private Boolean mSex;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photoNum")
    private Photo photo;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Pets> pets;

//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
//    private List<Board> board;
}
