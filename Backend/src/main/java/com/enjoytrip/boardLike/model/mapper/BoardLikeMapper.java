package com.enjoytrip.boardLike.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardLikeMapper {
    void likeBoard(int boardId, int memberId);

    void unlikeBoard(int boardId, int memberId);

    int getLikesCount(int boardId);

}
