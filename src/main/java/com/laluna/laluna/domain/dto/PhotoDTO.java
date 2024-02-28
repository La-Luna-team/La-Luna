package com.laluna.laluna.domain.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDTO {

    private Long photoNum;
    private Long boardId;
    private String link;

}
