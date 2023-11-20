package com.enjoytrip.comment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class CommentUpdateDto {
    @NotNull
    private int commentId;
    @NotBlank
    private String content;
    @NotBlank
    private String memberId;
}
