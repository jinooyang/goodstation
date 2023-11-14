package com.enjoytrip.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardListDto {

    private int boardId;
    private String title;
    private String memberId;
    private String createTime;
}
