package com.enjoytrip.commentLike.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CommentLikeDto {

    @NotNull
    private Integer commentId;

    @NotBlank
    private String memberId;
}
