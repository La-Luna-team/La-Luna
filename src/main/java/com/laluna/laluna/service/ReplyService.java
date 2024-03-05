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
        Reply reply = Reply.builder()
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

    public ReadReplyResponse replyRead(Long replyNum) {

        Reply findReply = replyRepository.findById(replyNum)
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
    public UpdateReplyResponse replyUpdate(Long replyNum, UpdateReplyRequest requestDTO){

        Reply findReply = replyRepository.findById(replyNum)
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
    public DeleteReplyResponse deleteReply(Long replyNum){

        Reply findReply = replyRepository.findById(replyNum)
                .orElseThrow(() -> new EntityNotFoundException("해당 id로 조회된 댓글이 없습니다"));

        replyRepository.delete(findReply);

        return new DeleteReplyResponse(findReply.getReplynum());
    }
}
