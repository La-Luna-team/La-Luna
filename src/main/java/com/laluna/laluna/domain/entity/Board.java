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
@ToString
public class Board extends Date{

    @Id
    @GeneratedValue
    private Long boardId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mNum")
    private Member member;

//    @Column(length = 50, nullable = false)
//    private String writer;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Photo> photos;

    public void update(List<Photo> photos, String title, String content, String category) {
        this.photos = photos;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
