package com.laluna.laluna.controller;

import com.laluna.laluna.domain.dto.reply.*;
import com.laluna.laluna.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;


    @PostMapping("/{replyNum}")
    public ResponseEntity<CreateReplyResponse> createReply(@RequestBody CreateReplyRequest requestDTO) {
        CreateReplyResponse response = replyService.replyCreate(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{replyNum}")
    public ResponseEntity<ReadReplyResponse> readReply(@PathVariable Long replyNum) {
        ReadReplyResponse response = replyService.replyRead(replyNum);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{replyNum}")
    public ResponseEntity<UpdateReplyResponse> updateReply(@PathVariable Long replyNum, @RequestBody UpdateReplyRequest requestDTO) {
        UpdateReplyResponse response = replyService.replyUpdate(replyNum, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{replyNum}")
    public ResponseEntity<DeleteReplyResponse> deleteReply(@PathVariable Long replyNum) {
        DeleteReplyResponse response = replyService.deleteReply(replyNum);
        return ResponseEntity.ok(response);
    }
}
