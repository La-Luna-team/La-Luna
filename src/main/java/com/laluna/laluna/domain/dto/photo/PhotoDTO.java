package com.laluna.laluna.domain.dto.photo;

import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDTO {


    private Long photono;

    private Member member;

    private String link;

    private Board board;

    public void Insert(Long photono, Member member, String link, Board board) {
        this.photono = photono;
        this.member = member;
        this.link = link;
        this.board = board;
    }
}
