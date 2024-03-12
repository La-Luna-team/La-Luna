package com.laluna.laluna.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LikeController {
    private int likeCount = 0;
    private int likeCount1 = 0;// 서버에서 관리하는 카운트 값

    @PostMapping("/increaseLikeCount")
    public ResponseEntity<String> increaseLikeCount(@RequestBody Map<String, Integer> requestBody) {
        int count = requestBody.get("count");
        likeCount = count; // 서버에서 카운트 값 갱신

        return ResponseEntity.ok("카운트가 성공적으로 갱신되었습니다.");
    }

    @GetMapping("/getLikeCount")
    public ResponseEntity<Integer> getLikeCount() {
        return ResponseEntity.ok(likeCount);
    }

    @PostMapping("/increaseLikeCount1")
    public ResponseEntity<String> increaseLikeCount1(@RequestBody Map<String, Integer> requestBody) {
        int count1 = requestBody.get("count1");
        likeCount1 = count1; // 서버에서 카운트 값 갱신

        return ResponseEntity.ok("카운트가 성공적으로 갱신되었습니다.");
    }

    @GetMapping("/getLikeCount1")
    public ResponseEntity<Integer> getLikeCount1() {
        return ResponseEntity.ok(likeCount1);
    }
}
