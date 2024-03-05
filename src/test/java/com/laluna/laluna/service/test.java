package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.board.CreateBoardRequest;
import com.laluna.laluna.domain.dto.board.CreateBoardResponse;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.domain.entity.Photo;
import com.laluna.laluna.repository.BoardRepository;
import groovy.transform.builder.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class test {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;
        @Test
        @Transactional
        @Rollback(false) // 테스트 후에도 데이터베이스에 데이터를 유지하기 위해 롤백을 비활성화합니다.
        @Builder
        public void testBoardCreate() {
            // given
            String title = "test title";
            String content = "test content";
            String category = "test category";
            List<Photo> link = new ArrayList<>();
            CreateBoardRequest requestDTO = CreateBoardRequest.builder()
                    .title(title)
                    .content(content)
                    .category(category)
                    .link(link)
                    .build();

            // when
            CreateBoardResponse responseDTO = boardService.boardCreate(requestDTO);

            // then
            assertNotNull(responseDTO); // responseDTO가 null이 아님을 확인합니다.

            Optional<Board> boardOptional = boardRepository.findById(responseDTO.getBoardid());
            assertTrue(boardOptional.isPresent()); // board가 데이터베이스에 존재함을 확인합니다.

            Board board = boardOptional.get();
            assertEquals(title, board.getTitle());
            assertEquals(content, board.getContent());
            assertEquals(category, board.getCategory());
            assertEquals(link, board.getPhotos());
        }
}
