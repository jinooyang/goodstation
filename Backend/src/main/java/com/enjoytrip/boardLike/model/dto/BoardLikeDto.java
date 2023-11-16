package com.enjoytrip.boardLike.model.dto;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class BoardLikeDto {

    @NotNull
    private int boardId;

    @NotNull
    private int memberId;
}
