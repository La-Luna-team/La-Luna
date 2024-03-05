package com.laluna.laluna.controller;

import com.laluna.laluna.domain.dto.reply.*;
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

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/boardview")
    public String createReply(@ModelAttribute CreateReplyRequest request, RedirectAttributes redirectAttributes){
        CreateReplyResponse response = replyService.replyCreate(request);
        redirectAttributes.addFlashAttribute("reply", response);
        return "redirect:/boards/boardview";
    }

    @GetMapping("/replylist")
    public String viewReply(@PathVariable Long replyNum, Model model) {
        ReadReplyResponse replyResponse = replyService.replyRead(replyNum);
        model.addAttribute("reply", replyResponse);
        return "boardview";
    }

    @PutMapping("/{replyNum}")
    public String updateReply(@PathVariable Long replyNum,@ModelAttribute UpdateReplyRequest requestDTO, RedirectAttributes redirectAttributes){
        replyService.replyUpdate(replyNum, requestDTO);
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
