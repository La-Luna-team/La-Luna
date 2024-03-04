package com.laluna.laluna.controller;

import com.laluna.laluna.domain.dto.reply.*;
import com.laluna.laluna.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {

    @Autowired
    private final ReplyService replyService;

    @PostMapping("/createReply")
    public String createReply(@ModelAttribute CreateReplyRequest requestDTO, RedirectAttributes redirectAttributes){
        CreateReplyResponse createReplyResponse = replyService.replyCreate(requestDTO);
        redirectAttributes.addFlashAttribute("message","댓글이 성공적으로 작성되었습니다. 댓글 ID: " + createReplyResponse.getReplynum());
        return "redirect:/view/boardview";
    }

    @GetMapping("/viewReply/{replynum}")
    public String viewReply(@PathVariable Long replynum, Model model) {
        ReadReplyResponse replyResponse = replyService.replyRead(replynum);
        model.addAttribute("reply", replyResponse);
        return "boardview";
    }

    @PutMapping("/{replynum}")
    public String updateReply(@PathVariable Long replynum,@ModelAttribute UpdateReplyRequest requestDTO, RedirectAttributes redirectAttributes){
        replyService.replyUpdate(replynum, requestDTO);
        redirectAttributes.addFlashAttribute("message","댓글이 성공적으로 수정되었습니다.");
        return "redirect:/view/boardview";
    }

    @DeleteMapping("/{replynum}")
    public String deleteReply(@PathVariable Long replynum, RedirectAttributes redirectAttributes){
        replyService.deleteReply(replynum);
        redirectAttributes.addFlashAttribute("message","댓글이 삭제되었습니다.");
        return "redirect:/view/boardview";
    }
}
