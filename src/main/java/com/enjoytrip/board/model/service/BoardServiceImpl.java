package com.enjoytrip.board.model.service;

import com.enjoytrip.board.model.dto.BoardWritingDto;
import com.enjoytrip.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public int writeBoard(BoardWritingDto boardWritingDto)  {
        if (boardMapper.writeBoard(boardWritingDto) == 0) {
            throw new RuntimeException();
        }

        return boardWritingDto.getBoardId();
    }
}
