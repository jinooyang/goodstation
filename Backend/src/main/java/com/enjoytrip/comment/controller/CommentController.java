package com.enjoytrip.comment.controller;

import com.enjoytrip.comment.model.dto.CommentDto;
import com.enjoytrip.comment.model.service.CommentService;
import com.enjoytrip.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.enjoytrip.response.StatusEnum;

import javax.validation.Valid;

@RestController
@RequestMapping("/board/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    public ResponseEntity<ResponseMessage> writeComment(@Valid @RequestBody CommentDto writeCommentDto) {
        ResponseMessage message = new ResponseMessage();
        if (writeCommentDto.getContent() == null || writeCommentDto.getContent().trim().isEmpty()) {
            message.setStatus(StatusEnum.FAIL);
            message.setMessage("내용을 입력하주세요.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            commentService.writeComment(writeCommentDto);
            message.setStatus(StatusEnum.OK);
            message.setMessage("댓글이 작성되었습니다.");
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
