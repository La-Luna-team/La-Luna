package com.laluna.laluna.domain.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long boardId;
    private String mNum;
    private String title;
    private String content;
    private String picture;
    private String category;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
