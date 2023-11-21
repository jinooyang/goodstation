package com.enjoytrip.board.controller;

import com.enjoytrip.board.model.dto.BoardListDto;
import com.enjoytrip.board.model.dto.BoardReadDto;
import com.enjoytrip.board.model.dto.BoardUpdateDto;
import com.enjoytrip.board.model.dto.BoardWritingDto;
import com.enjoytrip.board.model.service.BoardService;
import com.enjoytrip.boardLike.model.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardLikeService boardLikeService;

    @PostMapping
    public int writeBoard(@Valid @RequestBody BoardWritingDto boardWritingDto) {
        return boardService.writeBoard(boardWritingDto);
    }

    @GetMapping("/{boardId}")
    public BoardReadDto readBoard(@PathVariable int boardId) {
        return boardService.readBoard(boardId);
    }

    @PutMapping("/edit/{boardId}")
    public void updateBoard(@PathVariable int boardId, @Valid @RequestBody BoardUpdateDto boardUpdateDto) {
        boardService.updateBoard(boardId, boardUpdateDto);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable int boardId) {
        boardService.deleteBoard(boardId);
    }

    @GetMapping()
    public List<BoardListDto> getBoardList(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "12") int pageSize) {
        return boardService.getBoardList(pageNo, pageSize);
    }

    @GetMapping("/search")
    public List<BoardListDto> searchBoard(@RequestParam String searchType,
                                          @RequestParam String keyword) {

        return boardService.searchBoard(searchType, keyword);
    }


    @PutMapping("/{boardId}/like")
    public ResponseEntity<String> likeBoard(@PathVariable int boardId, @RequestParam String memberId) {

        if (!boardService.existsBoard(boardId)) {
            return new ResponseEntity<>("게시물이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        String authorId = boardService.readBoard(boardId).getMemberId();
        if (authorId.equals(memberId)) {
            return new ResponseEntity<>("추천할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        if (boardLikeService.hasUserLikedBoard(memberId, boardId)) {
            boardLikeService.unlikeBoard(boardId, memberId);
            return new ResponseEntity<>("추천을 취소하였습니다.", HttpStatus.OK);
        } else {
            boardLikeService.likeBoard(boardId, memberId);
            return new ResponseEntity<>("추천하였습니다.", HttpStatus.OK);
        }
    }

    @GetMapping("{boardId}/likes")
    public int getLikesCount(@PathVariable int boardId) {
        return boardLikeService.getLikesCount(boardId);
    }
}
