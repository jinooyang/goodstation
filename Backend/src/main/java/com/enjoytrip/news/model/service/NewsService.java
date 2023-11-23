package com.enjoytrip.news.model.service;

import com.enjoytrip.news.model.dto.NewsDto;
import java.util.List;

public interface NewsService {
    int writeNews(NewsDto NewsDto);

    List<NewsDto> getNewsList(int pageNo, int pageSize);

    NewsDto readNews(int newId);
}
