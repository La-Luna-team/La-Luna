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
    private Long replynum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardid")
    private Board board;

    private String replytext;

    private String replyer;

    public void update(String replyText,String replyer){
        this.replyText = replyText;
        this.replyer = replyer;
    }

}
