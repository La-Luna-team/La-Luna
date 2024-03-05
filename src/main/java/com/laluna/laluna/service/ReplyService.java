package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.reply.*;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.domain.entity.Reply;
import com.laluna.laluna.repository.BoardRepository;
import com.laluna.laluna.repository.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final BoardRepository boardRepository;

    @Transactional
    public CreateReplyResponse replyCreate(CreateReplyRequest requestDTO) {
        Board board = boardRepository.findById(requestDTO.getBoardid())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + requestDTO.getBoardid()));
        Reply reply = Reply.builder()
                .board(board)
                .replytext(requestDTO.getReplytext())
                .replyer(requestDTO.getReplyer())
                .build();
        Reply saveReply = replyRepository.save(reply);

        return new CreateReplyResponse(
                saveReply.getReplynum(),
                saveReply.getBoard().getBoardid(),
                saveReply.getReplytext(),
                saveReply.getReplyer(),
                saveReply.getBoard().getRegdate(),
                saveReply.getBoard().getModdate());
    }

    public ReadReplyResponse replyRead(Long replynum) {

        Reply findReply = replyRepository.findById(replynum)
                .orElseThrow(() -> new EntityNotFoundException("해당 id로 조회된 댓글이 없습니다"));

        return new ReadReplyResponse(
                findReply.getReplynum(),
                findReply.getBoard().getBoardid(),
                findReply.getReplytext(),
                findReply.getReplyer(),
                findReply.getBoard().getRegdate(),
                findReply.getBoard().getModdate());
    }

    @Transactional
    public UpdateReplyResponse replyUpdate(Long replynum, UpdateReplyRequest requestDTO){

        Reply findReply = replyRepository.findById(replynum)
                .orElseThrow(() -> new EntityNotFoundException("해당 id로 조회된 댓글이 없습니다"));

        findReply.update(requestDTO.getReplytext(),requestDTO.getReplyer());

        return new UpdateReplyResponse(
                findReply.getReplynum(),
                findReply.getBoard().getBoardid(),
                findReply.getReplytext(),
                findReply.getReplyer(),
                findReply.getBoard().getRegdate(),
                findReply.getBoard().getModdate());
    }

    @Transactional
    public DeleteReplyResponse deleteReply(Long replynum){

        Reply findReply = replyRepository.findById(replynum)
                .orElseThrow(() -> new EntityNotFoundException("해당 id로 조회된 댓글이 없습니다"));

        replyRepository.delete(findReply);

        return new DeleteReplyResponse(findReply.getReplynum());
    }
}
