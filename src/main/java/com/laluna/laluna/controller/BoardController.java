package com.laluna.laluna.controller;

import com.laluna.laluna.domain.dto.board.*;
import com.laluna.laluna.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public void createGET() {
    }
    @PostMapping("/register")
    public String createBoard(@ModelAttribute CreateBoardRequest requestDTO, RedirectAttributes redirectAttributes) {
        CreateBoardResponse responseDTO = boardService.boardCreate(requestDTO);

//      model.addAttribute("board", responseDTO);
//      return "boardView";   //해당 데이터는 현재 요청에서 처리되는 뷰에서만 사용할 수 있고 사용되면 더이상 사용불가

        redirectAttributes.addFlashAttribute("board", responseDTO);
        return "redirect:/boards/list";    //글작성후, 리다이렉트 해서 해당 페이지에 정보를 전달할 때 사용
    }

    @GetMapping("/read/{boardid}")
    public String readBoard(@PathVariable("boardid") Long boardid, Model model) {
        ReadBoardResponse responseDTO = boardService.boardRead(boardid);
        model.addAttribute("board", responseDTO);
        return "boards/boardview";
    }

    @PutMapping("/{boardid}")
    public String updateBoard(@PathVariable Long boardid,
                              @ModelAttribute UpdateBoardRequest requestDTO,
                              RedirectAttributes redirectAttributes) {
        UpdateBoardResponse responseDTO = boardService.boardUpdate(boardid, requestDTO);
        redirectAttributes.addFlashAttribute("board", responseDTO);
        return "redirect:/view/boardview/" + boardid;
    }

    @DeleteMapping("/{boardid}")
    public String deleteBoard(@PathVariable Long boardid, RedirectAttributes redirectAttributes) {
        DeleteBoardResponse responseDTO = boardService.boardDelete(boardid);
        redirectAttributes.addFlashAttribute("board", responseDTO);
        //게시글을 삭제후에 boardid에 해당하는 값을 받아와서 메세지로 띄울 경우가아니면  삭제해도 무방할듯싶다.
        return "redirect:/view/list";
    }

    @GetMapping("/list")
    public String listBoards(@RequestParam(defaultValue = "0") int page, Model model) {
        //@RequestParam(defaultValue = "0") int page 는 URL 의 쿼리 파라미터에서
        // page 값을 가져와서 int 타입의 page 변수에 바인딩하는 역할
        // http://localhost:8080/boards/list?page=1 -> 이런식

        Pageable pageable = PageRequest.of(page, 9, Sort.by("boardid").descending());
        Page<ReadBoardResponse> boardPage = boardService.boardList(pageable);
        model.addAttribute("boards", boardPage);
        return "/boards/boardlist";
    }

}
