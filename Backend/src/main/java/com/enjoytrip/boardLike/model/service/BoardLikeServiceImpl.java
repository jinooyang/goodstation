package com.enjoytrip.boardLike.model.service;

import com.enjoytrip.boardLike.model.mapper.BoardLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService{

    private final BoardLikeMapper boardLikeMapper;

    @Override
    public void likeBoard(int boardId, int memberId) {

    }

    @Override
    public void unlikeBoard(int boardId, int memberId) {

    }

    @Override
    public int getLikesCount(int boardId) {
        return boardLikeMapper.getLikesCount(boardId);
    }
}
