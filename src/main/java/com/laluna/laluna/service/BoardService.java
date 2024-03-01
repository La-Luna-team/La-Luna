package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.board.*;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    @Transactional
    public CreateBoardResponse boardCreate(CreateBoardRequest requestDTO) {
        Board board = Board.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .category(requestDTO.getCategory())
                .photos(requestDTO.getLink())
                .build();

        Board saveBoard= boardRepository.save(board);

        return new CreateBoardResponse(
                saveBoard.getBoardId(),
                saveBoard.getPhotos(),
                saveBoard.getTitle(),
                saveBoard.getContent(),
                saveBoard.getCategory(),
                saveBoard.getRegDate(),
                saveBoard.getModDate());
    }

    public ReadBoardResponse boardRead(Long boardId) {

        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 boardId로 조회된 게시글이 없습니다."));

        return new ReadBoardResponse(
                findBoard.getBoardId(),
                findBoard.getPhotos(),
                findBoard.getTitle(),
                findBoard.getContent(),
                findBoard.getCategory(),
                findBoard.getRegDate(),
                findBoard.getModDate());
    }

    @Transactional
    public UpdateBoardResponse boardUpdate(Long boardId, UpdateBoardRequest requestDTO) {

        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 boardId로 조회된 게시글이 없습니다."));

        findBoard.update(requestDTO.getLink(), requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getCategory());

        return new UpdateBoardResponse(
                findBoard.getBoardId(),
                findBoard.getPhotos(),
                findBoard.getTitle(),
                findBoard.getContent(),
                findBoard.getCategory(),
                findBoard.getRegDate(),
                findBoard.getModDate());
    }

    @Transactional
    public DeleteBoardResponse boardDelete(Long boardId) {

        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 boardId로 조회된 게시글이 없습니다."));

        boardRepository.delete(findBoard);

        return new DeleteBoardResponse(findBoard.getBoardId());
    }

    public Page<ReadBoardResponse> boardList(Pageable pageable) {

        Page<Board> boardPage = boardRepository.findAll(pageable);

        return boardPage.map(board -> new ReadBoardResponse(
                board.getBoardId(),
                board.getPhotos(),
                board.getTitle(),
                board.getContent(),
                board.getCategory(),
                board.getRegDate(),
                board.getModDate()
        ));
    }
}