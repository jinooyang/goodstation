package com.enjoytrip.boardLike.model.service;

import com.enjoytrip.boardLike.model.mapper.BoardLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService{

    private final BoardLikeMapper boardLikeMapper;


    @Override
    public boolean hasUserLikedBoard(String memberId, int boardId) {
        return boardLikeMapper.hasUserLikedBoard(memberId, boardId) > 0;
    }

    @Override
    public void likeBoard(int boardId, String memberId) {
        boardLikeMapper.likeBoard(boardId, memberId);
    }

    @Override
    public void unlikeBoard(int boardId, String memberId) {
        boardLikeMapper.unlikeBoard(boardId, memberId);
    }

    @Override
    public int getLikesCount(int boardId) {
        return boardLikeMapper.getLikesCount(boardId);
    }
}
