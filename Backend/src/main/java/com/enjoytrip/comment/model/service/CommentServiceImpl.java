package com.enjoytrip.comment.model.service;

import com.enjoytrip.comment.model.dto.CommentDto;
import com.enjoytrip.comment.model.dto.CommentUpdateDto;
import com.enjoytrip.comment.model.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public void writeComment(CommentDto writeCommentDto) {
        if (writeCommentDto == null) {
            throw new RuntimeException();
        }

        commentMapper.writeComment(writeCommentDto);
    }

    @Override
    public void updateComment(CommentUpdateDto updateCommentDto) {
        commentMapper.updateComment(updateCommentDto);
    }

    @Override
    public void deleteComment(int commentId) {
        commentMapper.deleteComment(commentId);
    }

    @Override
    public CommentDto getCommentDto(int commentId) {
        CommentDto commentDto = commentMapper.getCommentById(commentId);
        return commentDto;
    }

    @Override
    public List<CommentDto> getListByBoardId(int boardId) {
        return commentMapper.getCommentList(boardId);
    }
}
