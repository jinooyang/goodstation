package com.enjoytrip.board.controller;

import com.enjoytrip.board.model.dto.BoardWritingDto;
import com.enjoytrip.board.model.service.BoardService;
import com.enjoytrip.board.model.service.BoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BoardController boardController;

//    @Test
//    void registerBean() {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com/enjoytrip/board");
//        BoardController boardController = applicationContext.getBean("boardController", BoardController.class);
//        assertThat(boardController).isNotNull();
//    }

    @Test
    void writeBoard() {
        BoardWritingDto boardWritingDto = new BoardWritingDto(0,"Test", "Test", "test", 1);

        Assertions.assertThatCode(
                () -> mockMvc.perform(
                        MockMvcRequestBuilders.post("/board")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(boardWritingDto))
                )
        ).doesNotThrowAnyException();
    }

    private class MockBoardService implements BoardService {

        @Override
        public int writeBoard(BoardWritingDto boardWritingDto) {
            return 100;
        }
    }
}