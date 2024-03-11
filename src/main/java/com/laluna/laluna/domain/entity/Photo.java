package com.laluna.laluna.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue
    private Long photono;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberno")
    private Member member;

    @Column(length = 200, nullable = true)
    private String url;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardno")
    private Board board;



    public void updateUrl(String url) {
        this.url = url;
    }
}
