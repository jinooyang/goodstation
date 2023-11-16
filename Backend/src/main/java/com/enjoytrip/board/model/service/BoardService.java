package com.enjoytrip.board.model.service;

import com.enjoytrip.board.model.dto.BoardListDto;
import com.enjoytrip.board.model.dto.BoardReadDto;
import com.enjoytrip.board.model.dto.BoardWritingDto;

import java.util.List;

public interface BoardService {

    int writeBoard(BoardWritingDto boardWritingDto);

    BoardReadDto readBoard(int boardId);

    void updateBoard(int boardId, BoardWritingDto updatedBoardDto);

    void deleteBoard(int boardId);

    List<BoardListDto> getBoardList(int pageNo, int pageSize);

    List<BoardListDto> searchBoard(String searchType, String keyword);

    boolean existsBoard(int boardId);
}
