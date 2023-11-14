package com.enjoytrip.board.model.service;

import com.enjoytrip.board.model.dto.BoardListDto;
import com.enjoytrip.board.model.dto.BoardReadDto;
import com.enjoytrip.board.model.dto.BoardWritingDto;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    int writeBoard(BoardWritingDto boardWritingDto);

    BoardReadDto readBoard(int boardId);

    void updateBoard(int boardId, BoardWritingDto updatedBoardDto);

    void deleteBoard(int boardId);

    List<BoardListDto> getBoardList();

    List<BoardListDto> searchBoard(String searchType, String keyword);
}
