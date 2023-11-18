package com.enjoytrip.comment.model.mapper;

import com.enjoytrip.comment.model.dto.CommentDto;
import com.enjoytrip.comment.model.dto.CommentUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void writeComment(CommentDto writeCommentDto);

    List<CommentDto> getCommentList(int boardId);

    void updateComment(CommentUpdateDto updateCommentDto);

    void deleteComment(int commentId);

    CommentDto getCommentById(int commentId);
}
