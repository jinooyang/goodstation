package com.enjoytrip.comment.controller;

import com.enjoytrip.board.model.service.BoardService;
import com.enjoytrip.comment.model.dto.CommentDto;
import com.enjoytrip.comment.model.dto.CommentUpdateDto;
import com.enjoytrip.comment.model.service.CommentService;
import com.enjoytrip.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.enjoytrip.response.StatusEnum;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping()
    public ResponseEntity<ResponseMessage> writeComment(@Valid @RequestBody CommentDto writeCommentDto) {
        ResponseMessage message = new ResponseMessage();
        if (writeCommentDto.getContent() == null || writeCommentDto.getContent().trim().isEmpty()) {
            message.setStatus(StatusEnum.FAIL);
            message.setMessage("내용을 입력해주세요.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            commentService.writeComment(writeCommentDto);
            message.setStatus(StatusEnum.OK);
            message.setMessage("댓글이 작성되었습니다.");
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ResponseMessage> getCommentList(@PathVariable int boardId) {
        ResponseMessage message = new ResponseMessage();
        if (boardService.readBoard(boardId) == null) {
            message.setStatus(StatusEnum.FAIL);
            message.setMessage("댓글이 존재하지 않습니다.");
        } else {
            message.setData("commentList", commentService.getListByBoardId(boardId));
            message.setStatus(StatusEnum.OK);
            message.setMessage("댓글을 불러왔습니다.");
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ResponseMessage> updateComment(@RequestBody CommentUpdateDto updateCommentDto) {
        ResponseMessage message = new ResponseMessage();
        CommentDto commentDto = commentService.getCommentDto(updateCommentDto.getCommentId());
        if (commentDto == null) {
            message.setStatus(StatusEnum.FAIL);
            message.setMessage("댓글이 존재하지 않습니다.");
        } else {
            message.setStatus(StatusEnum.OK);
            message.setMessage("댓글이 수정되었습니다.");
            commentService.updateComment(updateCommentDto);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable int commentId) {
        ResponseMessage message = new ResponseMessage();
        CommentDto commentDto = commentService.getCommentDto(commentId);
        if (commentDto == null) {
            message.setStatus(StatusEnum.FAIL);
            message.setMessage("댓글이 존재하지 않습니다..");
        } else {
            commentService.deleteComment(commentId);
            message.setStatus(StatusEnum.OK);
            message.setMessage("댓글이 삭제되었습니다.");
        }
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }
}
