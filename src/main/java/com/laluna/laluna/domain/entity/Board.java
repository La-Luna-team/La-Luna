package com.laluna.laluna.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    private Long boardId;

    @Column(length = 500, nullable = false)
    private Long mNum;

    private String title;
    private String content;
    private String picture;
    private String category;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
