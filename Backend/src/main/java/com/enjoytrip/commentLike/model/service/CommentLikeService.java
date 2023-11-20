package com.enjoytrip.commentLike.model.service;

import com.enjoytrip.commentLike.model.dto.CommentLikeDto;

public interface CommentLikeService {

    boolean hasUserLikeComment(CommentLikeDto commentLikeDto);

    void likeComment(CommentLikeDto commentLikeDto);

    void unlikeComment(CommentLikeDto commentLikeDto);

    int getLikesCount(int commentId);
}
