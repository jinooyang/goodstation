package com.enjoytrip.boardLike.model.service;

public interface BoardLikeService {

    boolean hasUserLikedBoard(String memberId, int boardId);

    void likeBoard(int boardId, String memberId);

    void unlikeBoard(int boardId, String memberId);

    int getLikesCount(int boardId);
}
