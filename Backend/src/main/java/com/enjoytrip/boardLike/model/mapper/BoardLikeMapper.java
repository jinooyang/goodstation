package com.enjoytrip.boardLike.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardLikeMapper {
    int hasUserLikedBoard(String memberId, int boardId);

    void likeBoard(int boardId, String memberId);

    void unlikeBoard(int boardId, String memberId);

    int getLikesCount(int boardId);

}
