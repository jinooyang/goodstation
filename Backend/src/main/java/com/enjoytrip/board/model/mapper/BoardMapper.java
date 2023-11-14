package com.enjoytrip.board.model.mapper;

import com.enjoytrip.board.model.dto.BoardReadDto;
import com.enjoytrip.board.model.dto.BoardWritingDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    int writeBoard(BoardWritingDto boardWritingDto);

    BoardReadDto readBoard(int boardId);

    void updateBoard(BoardWritingDto boardUpdateDto);

    void deleteBoard(int boardId);

}
