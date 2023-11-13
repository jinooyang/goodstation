package com.enjoytrip.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BoardReadDto {

    private String title;
    private String content;
    private LocalDateTime createTime;
    private String memberId;
    private String tripId;
}
