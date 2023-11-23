package com.enjoytrip.news.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class NewsDto {

    private int newsId;

    @NotBlank
    private String title;

    private String memberRole;

    @NotBlank
    private String content;

    private String createTime;
}
