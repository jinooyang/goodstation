package com.enjoytrip.commentLike.model.mapper;

import com.enjoytrip.commentLike.model.dto.CommentLikeDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentLikeMapper {

    int hasUserLikeComment(CommentLikeDto commentLikeDto);

    void likeComment(CommentLikeDto commentLikeDto);

    void unlikeComment(CommentLikeDto commentLikeDto);

    int getLikesCount(int commentId);
}
