package com.enjoytrip.commentLike.model.service;

import com.enjoytrip.commentLike.model.dto.CommentLikeDto;
import com.enjoytrip.commentLike.model.mapper.CommentLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeMapper commentLikeMapper;

    @Override
    public boolean hasUserLikeComment(CommentLikeDto commentLikeDto) {
        return commentLikeMapper.hasUserLikeComment(commentLikeDto) > 0;
    }

    @Override
    public void likeComment(CommentLikeDto commentLikeDto) {
        commentLikeMapper.likeComment(commentLikeDto);
    }

    @Override
    public void unlikeComment(CommentLikeDto commentLikeDto) {
        commentLikeMapper.unlikeComment(commentLikeDto);
    }

    @Override
    public int getLikesCount(int commentId) {
        return commentLikeMapper.getLikesCount(commentId);
    }
}
