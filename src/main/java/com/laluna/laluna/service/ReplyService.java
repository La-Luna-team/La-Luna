package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.reply.*;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.domain.entity.Reply;
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

    @Transactional
    public CreateReplyResponse replyCreate(CreateReplyRequest requestDTO) {
        Board board = new Board();
        Reply reply = Reply.builder()
                .replyText(requestDTO.getReplyText())
                .replyer(requestDTO.getReplyer())
                .board(board)
                .build();
        Reply saveReply = replyRepository.save(reply);

        return new CreateReplyResponse(
                saveReply.getReplyNum(),
                saveReply.getBoard().getBoardId(),
                saveReply.getReplyText(),
                saveReply.getReplyer(),
                saveReply.getBoard().getRegDate(),
                saveReply.getBoard().getModDate());
    }

    public ReadReplyResponse replyRead(Long replyNum) {

        Reply findReply = replyRepository.findById(replyNum)
                .orElseThrow(() -> new EntityNotFoundException("해당 id로 조회된 댓글이 없습니다"));

        return new ReadReplyResponse(
                findReply.getReplyNum(),
                findReply.getBoard().getBoardId(),
                findReply.getReplyText(),
                findReply.getReplyer(),
                findReply.getBoard().getRegDate(),
                findReply.getBoard().getModDate());
    }

    @Transactional
    public UpdateReplyResponse replyUpdate(Long replyNum, UpdateReplyRequest requestDTO){

        Reply findReply = replyRepository.findById(replyNum)
                .orElseThrow(() -> new EntityNotFoundException("해당 id로 조회된 댓글이 없습니다"));

        findReply.update(requestDTO.getReplyText(),requestDTO.getReplyer());

        return new UpdateReplyResponse(
                findReply.getReplyNum(),
                findReply.getBoard().getBoardId(),
                findReply.getReplyText(),
                findReply.getReplyer(),
                findReply.getBoard().getRegDate(),
                findReply.getBoard().getModDate());
    }

    @Transactional
    public DeleteReplyResponse deleteReply(Long replyNum){

        Reply findReply = replyRepository.findById(replyNum)
                .orElseThrow(() -> new EntityNotFoundException("해당 id로 조회된 댓글이 없습니다"));

        replyRepository.delete(findReply);

        return new DeleteReplyResponse(findReply.getReplyNum());
    }
}
