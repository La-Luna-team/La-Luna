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
    private Long boardid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mnum")
    private Member member;

//    @Column(length = 50, nullable = false)
//    private String writer;

    @Column(length = 50)
    private String title;

    @Column(length = 1000)
    private String content;

    @Column(length = 50)
    private String category;

    @Column(length = 255) // 이미지 URL이 저장될 필드 추가
    private String imageUrl;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Photo> photos;

    public void update(List<Photo> photos, String title, String content, String category) {

        this.photos = photos;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
