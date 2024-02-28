package com.laluna.laluna.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends Date{

    @Id
    @GeneratedValue
    private Long boardId;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mNum")
    private Member member;*/
    @Column(length = 50, nullable = false)
    private String writer;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String category;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Photo> photo;
}
