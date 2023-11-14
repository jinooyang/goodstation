package com.enjoytrip.board.controller;

import com.enjoytrip.board.model.dto.BoardReadDto;
import com.enjoytrip.board.model.dto.BoardWritingDto;
import com.enjoytrip.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

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
}
