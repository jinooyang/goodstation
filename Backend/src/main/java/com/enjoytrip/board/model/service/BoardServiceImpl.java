package com.enjoytrip.board.model.service;

import com.enjoytrip.board.model.dto.BoardListDto;
import com.enjoytrip.board.model.dto.BoardReadDto;
import com.enjoytrip.board.model.dto.BoardUpdateDto;
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
    public void updateBoard(int boardId, BoardUpdateDto updatedBoardDto) {
        BoardReadDto boardReadDto = boardMapper.readBoard(boardId);

        if (boardReadDto == null) {
            throw new RuntimeException();
        }
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto(boardId, updatedBoardDto.getTitle(),
                updatedBoardDto.getContent(), updatedBoardDto.getTripId());

        boardMapper.updateBoard(boardUpdateDto);
    }

    @Override
    public void deleteBoard(int boardId) {
        BoardReadDto boardReadDto = boardMapper.readBoard(boardId);

        if (boardReadDto == null) {
            throw new RuntimeException();
        }
        boardMapper.deleteAllCommentInBoard(boardId);
        boardMapper.deleteBoard(boardId);
    }

    @Override
    public List<BoardListDto> getBoardList(int pageNo, int pageSize) {
        int offset = (pageNo - 1) * pageSize;
        return boardMapper.getBoardList(pageSize, offset);
    }

    @Override
    public List<BoardListDto> searchBoard(String searchType, String keyword, int pageNo, int pageSize) {
        int offset = (pageNo - 1) * pageSize;
        return boardMapper.searchBoard(searchType, keyword, pageSize, offset);
    }

    @Override
    public boolean existsBoard(int boardId) {
        return boardMapper.readBoard(boardId) != null;
    }

    @Override
    public int countBoard() {
        return boardMapper.countBoard();
    }

    @Override
    public int countSearchResults(String searchType, String keyword) {
        return boardMapper.countSearchResults(searchType, keyword);
    }
}
