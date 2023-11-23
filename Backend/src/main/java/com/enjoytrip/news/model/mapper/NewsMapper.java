package com.enjoytrip.news.model.mapper;

import com.enjoytrip.news.model.dto.NewsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {

    int writeNews(NewsDto NewsDto);

    List<NewsDto> getNewsList(int pageSize, int offset);

    NewsDto readNews(int newsId);


    Integer lastNews();
}
