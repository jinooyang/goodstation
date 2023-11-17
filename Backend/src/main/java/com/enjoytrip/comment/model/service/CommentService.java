package com.enjoytrip.comment.model.service;

import com.enjoytrip.comment.model.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void writeComment(CommentDto writeCommentDto);
    List<CommentDto> getCommentList(int board_id);
    void updateComment(int commentId, CommentDto updateCommentDto);
    void deleteComment(int commentId);

    CommentDto getCommentDto(int commentId);

    List<CommentDto> getListByBoardId(int boardId);
}
