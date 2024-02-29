package com.laluna.laluna.domain.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReplyResponse {

    private Long replyNum;

    private Long boardId;

    private String replyText;

    private String replyer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
