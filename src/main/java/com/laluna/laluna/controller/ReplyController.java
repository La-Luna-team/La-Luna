package com.laluna.laluna.controller;

import com.laluna.laluna.domain.dto.reply.*;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.repository.BoardRepository;
import com.laluna.laluna.service.BoardService;
import com.laluna.laluna.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/boardview")
    public String createReply(@ModelAttribute CreateReplyRequest request, RedirectAttributes redirectAttributes){
        CreateReplyResponse response = replyService.replyCreate(request);
        redirectAttributes.addFlashAttribute("reply", response);
        return "redirect:/boards/read/"+ request.getBoardid();
    }

    @GetMapping("/replylist/{boardid}")
    public String viewReply(@PathVariable("boardid") Long boardid, Model model) {
        List<ReadReplyResponse> replyResponses = replyService.getRepliesByBoardId(boardid);
        model.addAttribute("reply", replyResponses);
        return "boards/boardview";
    }


    @PutMapping("/{replyNum}")
    public String updateReply(@PathVariable Long replynum,@ModelAttribute UpdateReplyRequest requestDTO, RedirectAttributes redirectAttributes){
        replyService.replyUpdate(replynum, requestDTO);
        redirectAttributes.addFlashAttribute("message","ㅁ댓글이 성공적으로 수정되었습니다.");
        return "redirect:/view/boardview";
    }

    @DeleteMapping("/{replynum}")
    public String deleteReply(@PathVariable Long replynum, RedirectAttributes redirectAttributes){
        replyService.deleteReply(replynum);
        redirectAttributes.addFlashAttribute("message","댓글이 삭제되었습니다.");
        return "redirect:/view/boardview";
    }
}
