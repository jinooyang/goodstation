package com.enjoytrip.board.model.mapper;

import com.enjoytrip.board.model.dto.BoardListDto;
import com.enjoytrip.board.model.dto.BoardReadDto;
import com.enjoytrip.board.model.dto.BoardUpdateDto;
import com.enjoytrip.board.model.dto.BoardWritingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int writeBoard(BoardWritingDto boardWritingDto);

    BoardReadDto readBoard(int boardId);

    void updateBoard(BoardUpdateDto boardUpdateDto);

    void deleteBoard(int boardId);

    List<BoardListDto> getBoardList(int pageSize, int offset);

    List<BoardListDto> searchBoard(String searchType, String keyword);

}
