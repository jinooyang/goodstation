package com.enjoytrip.comment.model.mapper;

import com.enjoytrip.comment.model.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void writeComment(CommentDto writeCommentDto);

    List<CommentDto> getCommentList(int board_id);

    void updateComment(int commentId, CommentDto updateCommentDto);

    void deleteComment(int commentId);
}
