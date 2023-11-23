package com.enjoytrip.news.model.service;

import com.enjoytrip.news.model.dto.NewsDto;
import com.enjoytrip.news.model.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsMapper newsMapper;

    @Override
    public int writeNews(NewsDto NewsDto) {
        if (newsMapper.writeNews(NewsDto) == 0) {
            throw new RuntimeException();
        }

        Integer last = newsMapper.lastNews();
        return last;
    }

    @Override
    public List<NewsDto> getNewsList(int pageNo, int pageSize) {
        int offset = (pageNo - 1) * pageSize;
        return newsMapper.getNewsList(pageSize, offset);
    }

    @Override
    public NewsDto readNews(int newId) {
        NewsDto newsReadDto = newsMapper.readNews(newId);

        if (newsReadDto == null) {
            throw new RuntimeException();
        }

        return newsReadDto;
    }
}
