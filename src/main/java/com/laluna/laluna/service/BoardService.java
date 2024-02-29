package com.laluna.laluna.service;


import com.laluna.laluna.domain.dto.board.*;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public CreateBoardResponse createBoard(CreateBoardRequest request) {

        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .build();

        Board savedBoard = boardRepository.save(board);

        return new CreateBoardResponse(savedBoard.getBoardId(), savedBoard.getTitle(), savedBoard.getContent(), savedBoard.getCategory(), savedBoard.getRegDate(), savedBoard.getModDate());
    }

    @Transactional
    public DeleteBoardResponse deleteBoard(Long boardId){

        Board foundBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 boardId로 조회된 게시글이 없습니다."));

        boardRepository.delete(foundBoard);

        return new DeleteBoardResponse(foundBoard.getBoardId());
    }

    public ReadBoardResponse readBoard(Long postId) {

        Board foundBoard = boardRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 boardId로 조회된 게시글이 없습니다."));

        return new ReadBoardResponse(foundBoard.getBoardId(), foundBoard.getTitle(), foundBoard.getContent(), foundBoard.getCategory(), foundBoard.getRegDate(), foundBoard.getModDate());
    }

    @Transactional
    public UpdateBoardResponse updateBoard(Long postId, UpdateBoardRequest request) {

        Board foundBoard = boardRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 boardId로 조회된 게시글이 없습니다."));

        //Dirty Checking
        foundBoard.update(request.getTitle(), request.getContent(), request.getCategory());

        return new UpdateBoardResponse(foundBoard.getBoardId(), foundBoard.getTitle(), foundBoard.getContent(), foundBoard.getCategory());
    }
}
