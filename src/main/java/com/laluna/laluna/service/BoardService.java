package com.laluna.laluna.service;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.board.*;
import com.laluna.laluna.domain.dto.pet.PetResponse;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.repository.BoardRepository;
import com.laluna.laluna.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public CreateBoardResponse boardCreate(@AuthenticationPrincipal MyUserDetails userDetails,
                                           CreateBoardRequest requestDTO) {
        String mid = userDetails.getUsername();  // 로그인한 사용자의 아이디를 가져옴
        Member member = memberRepository.findBymid(mid)  // 아이디로 Member 객체를 찾음
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + mid));

        Board board = Board.builder()
                .member(member)  // Member를 설정함
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .category(requestDTO.getCategory())
                .photos(requestDTO.getLink())
                .build();

        Board saveBoard = boardRepository.save(board);

        return new CreateBoardResponse(
                saveBoard.getBoardid(),
                saveBoard.getPhotos(),
                saveBoard.getTitle(),
                saveBoard.getContent(),
                saveBoard.getCategory(),
                saveBoard.getRegdate(),
                saveBoard.getModdate());
    }

    public ReadBoardResponse boardRead(Long boardid) {
        Board findBoard = boardRepository.findById(boardid)
                .orElseThrow(() -> new EntityNotFoundException("해당 boardid로 조회된 게시글이 없습니다."));

        String mid = findBoard.getMember().getMid(); // 작성자 아이디 가져오기

        List<PetResponse> petResponses = new ArrayList<>();
        if (findBoard.getMember().getPets() != null) {
            petResponses = findBoard.getMember().getPets().stream()
                    .map(PetResponse::new) // PetResponse 변환
                    .collect(Collectors.toList());
        }

        return new ReadBoardResponse(
                findBoard.getBoardid(),
                findBoard.getPhotos(),
                findBoard.getTitle(),
                findBoard.getContent(),
                findBoard.getCategory(),
                findBoard.getRegdate(),
                findBoard.getModdate(),
                mid, // 작성자 아이디 전달
                petResponses); // 펫 정보 리스트 전달
    }

    @Transactional
    public UpdateBoardResponse boardUpdate(Long boardid, UpdateBoardRequest requestDTO) {

        Board findBoard = boardRepository.findById(boardid)
                .orElseThrow(() -> new EntityNotFoundException("해당 boardid로 조회된 게시글이 없습니다."));

        findBoard.update(requestDTO.getLink(), requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getCategory());

        return new UpdateBoardResponse(
                findBoard.getBoardid(),
                findBoard.getPhotos(),
                findBoard.getTitle(),
                findBoard.getContent(),
                findBoard.getCategory(),
                findBoard.getRegdate(),
                findBoard.getModdate());
    }

    @Transactional
    public DeleteBoardResponse boardDelete(Long boardid) {

        Board findBoard = boardRepository.findById(boardid)
                .orElseThrow(() -> new EntityNotFoundException("해당 boardid로 조회된 게시글이 없습니다."));

        boardRepository.delete(findBoard);

        return new DeleteBoardResponse(findBoard.getBoardid());
    }

    public Page<ReadBoardResponse> boardList(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);

        return boardPage.map(board -> {
            String mid = board.getMember().getMid(); // 작성자 아이디 가져오기

            List<PetResponse> petResponses = new ArrayList<>();
            if (board.getMember().getPets() != null) {
                petResponses = board.getMember().getPets().stream()
                        .map(PetResponse::new) // PetResponse 변환
                        .collect(Collectors.toList());
            }

            return new ReadBoardResponse(
                    board.getBoardid(),
                    board.getPhotos(),
                    board.getTitle(),
                    board.getContent(),
                    board.getCategory(),
                    board.getRegdate(),
                    board.getModdate(),
                    mid, // 작성자 아이디 전달
                    petResponses); // 펫 정보 리스트 전달
        });
    }
    public List<Board> getBoardsByCategory(String category) {
        return boardRepository.findByCategory(category);
    }

    public List<Board> getBoardsByTitle(String title) {
        return boardRepository.findByTitle(title);
    }
}