package com.laluna.laluna.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardId")
    private Board board;

    private String replyText;

    private String replyer;

    public Reply(Long replyNum, String replyText, String replyer) {
        this.replyNum = replyNum;
        this.replyText = replyText;
        this.replyer = replyer;
    }

    public void update(String replyText,String replyer){
        this.replyText = replyText;
        this.replyer = replyer;
    }

}
