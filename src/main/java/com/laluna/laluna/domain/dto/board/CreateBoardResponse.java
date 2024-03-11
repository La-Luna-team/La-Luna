package com.laluna.laluna.domain.dto.board;

import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.domain.entity.Photo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBoardResponse {

    private Long boardno;

    private List<Photo> imageUrls; //이미지 링크

    private String title;   //게시글 제목

    private String content; //게시글 내용

    private String category; //게시글 카테고리 (건강, 팁, 일상)

    private LocalDateTime regdate;

    private LocalDateTime moddate;


    @Builder
    public CreateBoardResponse(Board board) {
        this.boardno = board.getBoardno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.category = board.getCategory();
        this.regdate = board.getRegdate();
        this.moddate = board.getModdate();
        // 이부분 추가
        this.imageUrls = board.getPhotos();
    }
}
