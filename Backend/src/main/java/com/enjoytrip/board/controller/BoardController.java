package com.enjoytrip.board.controller;

import com.enjoytrip.board.model.dto.BoardListDto;
import com.enjoytrip.board.model.dto.BoardReadDto;
import com.enjoytrip.board.model.dto.BoardWritingDto;
import com.enjoytrip.board.model.service.BoardService;
import com.enjoytrip.boardLike.model.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
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

    @PutMapping("/{boardId}")
    public void updateBoard(@PathVariable int boardId, @Valid @RequestBody BoardWritingDto updatedBoardDto) {
        boardService.updateBoard(boardId, updatedBoardDto);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable int boardId) {
        boardService.deleteBoard(boardId);
    }

    @GetMapping()
    public List<BoardListDto> getBoardList(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return boardService.getBoardList(pageNo, pageSize);
    }

    @GetMapping("/search")
    public List<BoardListDto> searchBoard(@RequestParam String searchType,
                                          @RequestParam String keyword) {

        return boardService.searchBoard(searchType, keyword);
    }

    @GetMapping("{boardId}/likes")
    public int getLikesCount(@PathVariable int boardId) {
        return boardLikeService.getLikesCount(boardId);
    }
}
