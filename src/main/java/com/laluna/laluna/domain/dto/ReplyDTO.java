package com.laluna.laluna.domain.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Long replyNum;
    private Long boardId;
    private String mId;
    private String reply;

}
