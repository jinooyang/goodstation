package com.enjoytrip.board.model.service;

import com.enjoytrip.board.model.dto.BoardListDto;
import com.enjoytrip.board.model.dto.BoardReadDto;
import com.enjoytrip.board.model.dto.BoardWritingDto;
import com.enjoytrip.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public int writeBoard(BoardWritingDto boardWritingDto) {
        if (boardMapper.writeBoard(boardWritingDto) == 0) {
            throw new RuntimeException();
        }

        return boardWritingDto.getBoardId();
    }

    @Override
    public BoardReadDto readBoard(int boardId) {
        BoardReadDto boardReadDto = boardMapper.readBoard(boardId);

        if (boardReadDto == null) {
            throw new RuntimeException();
        }

        return boardReadDto;
    }

    @Override
    public void updateBoard(int boardId, BoardWritingDto updatedBoardDto) {
        BoardReadDto boardReadDto = boardMapper.readBoard(boardId);

        if (boardReadDto == null) {
            throw new RuntimeException();
        }

        BoardWritingDto boardUpdateDto = new BoardWritingDto(boardId, updatedBoardDto.getTitle(),
                updatedBoardDto.getContent(), updatedBoardDto.getMemberId(), updatedBoardDto.getTripId());

        boardMapper.updateBoard(boardUpdateDto);
    }

    @Override
    public void deleteBoard(int boardId) {
        BoardReadDto boardReadDto = boardMapper.readBoard(boardId);

        if (boardReadDto == null) {
            throw new RuntimeException();
        }

        boardMapper.deleteBoard(boardId);
    }

    @Override
    public List<BoardListDto> getBoardList() {
        List<BoardListDto> boardList = boardMapper.getBoardList();
        if (boardList.isEmpty()) {
            throw new RuntimeException();
        }

        return boardMapper.getBoardList();
    }
}
