package com.laluna.laluna.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mnum;

    @Column(length = 20, nullable = false, unique = true)
    private String mid;

    @Column(length = 500, nullable = false)
    private String mpw;

    private String roles;

    private String mphone;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String email;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Pets> pets;

//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
//    private List<Board> board;

    public static Member createUser(String mid, String mpw, PasswordEncoder passwordEncoder, String mphone, String address,
                                    String email) {

        return Member.builder()
                .mid(mid)
                .mpw(passwordEncoder.encode(mpw))
                .roles("USER")
                .mphone(mphone)
                .address(address)
                .email(email)
                .build();
    }

}
