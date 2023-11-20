package com.enjoytrip.comment.controller;

import com.enjoytrip.board.model.service.BoardService;
import com.enjoytrip.comment.model.dto.CommentDto;
import com.enjoytrip.comment.model.dto.CommentUpdateDto;
import com.enjoytrip.comment.model.service.CommentService;
import com.enjoytrip.commentLike.model.dto.CommentLikeDto;
import com.enjoytrip.commentLike.model.service.CommentLikeService;
import com.enjoytrip.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.enjoytrip.response.StatusEnum;

import javax.validation.Valid;

@RestController
@RequestMapping("/board/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;
    private final CommentLikeService commentLikeService;

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
            message.setMessage("댓글이 존재하지 않습니다.");
        } else {
            commentService.deleteComment(commentId);
            message.setStatus(StatusEnum.OK);
            message.setMessage("댓글이 삭제되었습니다.");
        }
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @PutMapping("/like")
    public ResponseEntity<ResponseMessage> likeComment(@RequestBody CommentLikeDto commentLikeDto) {
        ResponseMessage message = new ResponseMessage();
        if (commentLikeDto.getCommentId() == null) {
            message.setStatus(StatusEnum.FAIL);
            message.setMessage("댓글이 존재하지 않습니다.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        //본인이 쓴 댓글 추천 불가능
        String authorId = commentService.getCommentDto(commentLikeDto.getCommentId())
                .getMemberId();
        if (authorId.equals(commentLikeDto.getMemberId())) {
            message.setStatus(StatusEnum.FAIL);
            message.setMessage("추천할 수 없습니다.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        if (commentLikeService.hasUserLikeComment(commentLikeDto)) {
            commentLikeService.unlikeComment(commentLikeDto);
            message.setMessage("추천이 취소되었습니다.");
            message.setStatus(StatusEnum.OK);
        } else {
            commentLikeService.likeComment(commentLikeDto);
            message.setMessage("추천하였습니다.");
            message.setStatus(StatusEnum.OK);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("{commentId}/likes")
    public int getLikesCount(@PathVariable int commentId) {
        return commentLikeService.getLikesCount(commentId);
    }
}
