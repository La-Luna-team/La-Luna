package com.laluna.laluna.controller;

import com.laluna.laluna.domain.dto.board.*;
import com.laluna.laluna.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
// @RequestMapping : 특정 URL을 매핑하게 도와준다.
@RequestMapping()
// @RequiredArgsConstructor : final 혹은 @NonNull 어노테이션이 붙은 필드에 대한 생성자를 자동으로 생성해준다.
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @Operation(summary = "게시글 작성", description = "제목(title), 내용(content) 입력해주세요")
    public ResponseEntity<CreateBoardResponse> BoardCreate(@RequestBody CreateBoardRequest request) {

        CreateBoardResponse response = boardService.createBoard(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<ReadBoardResponse> BoardRead(@PathVariable Long boardId) {

        ReadBoardResponse response = boardService.readBoard(boardId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<UpdateBoardResponse> BoardUpdate(@PathVariable Long boardId, @RequestBody UpdateBoardRequest request) {

        UpdateBoardResponse response = boardService.updateBoard(boardId, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<DeleteBoardResponse> BoardDelete(@PathVariable Long boardId) {

        DeleteBoardResponse response = boardService.deleteBoard(boardId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
