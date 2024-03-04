package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.reply.*;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.domain.entity.Reply;
import com.laluna.laluna.repository.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReplyServiceTest {

    @Mock
    private ReplyRepository replyRepository;

    @InjectMocks
    private ReplyService replyService;

    private Reply saveReply;

    private Reply reply;

    private CreateReplyRequest createReplyRequest;

    private UpdateReplyRequest updateReplyRequest;

    @BeforeEach
    void setup(){
        Board board = new Board();
        reply = new Reply(1L, board, "댓글 내용", "유저");
        saveReply = new Reply(2L, board, "댓글 내용", "유저");
        createReplyRequest = new CreateReplyRequest("댓글내용", "유저");
        updateReplyRequest = new UpdateReplyRequest("변경된 내용", "변경된 유저");
    }
    @Test
    @DisplayName("댓글 작성 테스트")
    public void testReplyCreate(){

        given(replyRepository.save(any())).willReturn(reply);

        CreateReplyResponse createReplyResponse = replyService.replyCreate(createReplyRequest);

        assertThat(createReplyResponse.getReplyNum()).isEqualTo(1L);
        assertThat(createReplyResponse.getBoardId()).isNull();
        assertThat(createReplyResponse.getReplyText()).isEqualTo("댓글 내용");
        assertThat(createReplyResponse.getReplyer()).isEqualTo("유저");
    }

    @Test
    @DisplayName("댓글 조회 테스트")
    void readReply(){
        when(replyRepository.findById(any())).thenReturn(Optional.of(saveReply));

        ReadReplyResponse readReplyResponse = replyService.replyRead(saveReply.getReplyNum());

        assertThat(readReplyResponse.getReplyNum()).isEqualTo(saveReply.getReplyNum());
        assertThat(readReplyResponse.getReplyText()).isEqualTo(saveReply.getReplyText());
        assertThat(readReplyResponse.getReplyer()).isEqualTo(saveReply.getReplyer());
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateReply(){
        given(replyRepository.findById(any())).willReturn(Optional.of(saveReply));

        UpdateReplyResponse updateReplyResponse = replyService.replyUpdate(saveReply.getReplyNum(), updateReplyRequest);

        assertThat(updateReplyResponse.getReplyText()).isEqualTo("변경된 내용");
        assertThat(updateReplyResponse.getReplyer()).isEqualTo("변경된 유저");
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteReply(){
        when(replyRepository.findById(any())).thenReturn(Optional.of(saveReply));

        DeleteReplyResponse response = replyService.deleteReply(saveReply.getReplyNum());

        assertThat(response.getReplyNum()).isEqualTo(2L);
    }

    @Test
    @DisplayName("예외처리")
    void deleteReply2(){

        given(replyRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                replyService.replyRead(1L));
    }
}
