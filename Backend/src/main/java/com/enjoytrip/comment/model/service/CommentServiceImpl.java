package com.enjoytrip.comment.model.service;

import com.enjoytrip.comment.model.dto.CommentDto;
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
    public List<CommentDto> getCommentList(int board_id) {
        return null;
    }

    @Override
    public void updateComment(int commentId, CommentDto updateCommentDto) {

    }

    @Override
    public void deleteComment(int commentId) {

    }
}
