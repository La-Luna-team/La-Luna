package com.laluna.laluna.domain.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReplyResponse {

    private Long replynum;

    private Long boardid;

    private String replytext;

    private String replyer;

    private LocalDateTime regdate;

    private LocalDateTime moddate;
}
