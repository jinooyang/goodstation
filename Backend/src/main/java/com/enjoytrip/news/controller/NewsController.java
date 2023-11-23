package com.enjoytrip.news.controller;

import com.enjoytrip.news.model.dto.NewsDto;
import com.enjoytrip.news.model.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public int writeNews(@Valid @RequestBody NewsDto NewsDto) {
        return newsService.writeNews(NewsDto);
    }

    @GetMapping
    public List<NewsDto> getNewsList(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
        return newsService.getNewsList(pageNo, pageSize);
    }

    @GetMapping("{newsId}")
    public NewsDto readNews(@PathVariable int newsId) {
        return newsService.readNews(newsId);
    }
}
