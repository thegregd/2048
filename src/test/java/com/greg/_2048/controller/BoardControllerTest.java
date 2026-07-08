package com.greg._2048.controller;

import com.greg._2048.command.Move;
import com.greg._2048.command.MoveUp;
import com.greg._2048.converter.DirectionConverter;
import com.greg._2048.model.Direction;
import com.greg._2048.model.Game;
import com.greg._2048.model.InProgress;
import com.greg._2048.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebMvcTest(BoardController.class)
class BoardControllerTest {
    
    @Autowired
    private MockMvcTester mockMvcTester;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
    private BoardService boardService;
    
    @MockitoBean
    private DirectionConverter directionConverter;
    
    @Test
    @DisplayName("Should return a new Game as JSON")
    void shouldReturnNewGameAsJson() {
        // given
        int[][] newBoard = {
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0}
        };
        Game givenGame = new Game(newBoard, new InProgress("New game started."), 0, "");
        given(boardService.createNewBoard()).willReturn(givenGame);
        String expected = objectMapper.writeValueAsString(givenGame);
        
        // when
        MvcTestResult actual = mockMvcTester.post().uri("/game/start").exchange();
        
        // then
        assertThat(actual)
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo(expected);
    }
    
    @Test
    @DisplayName("Should return a board as JSON after moving tiles up")
    void shouldReturnMovedUpBoardAsJson() {
        // given
        int[][] board = {
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0}
        };
        Move<int[][]> up = new MoveUp();
        Game givenGame = new Game(board, new InProgress("Carry on."), 0, "");
        given(directionConverter.convert("up")).willReturn(Direction.UP);
        given(directionConverter.convert(Direction.UP)).willReturn(up);
        given(boardService.move(up)).willReturn(givenGame);
        String expected = objectMapper.writeValueAsString(givenGame);
        
        // when
        MvcTestResult actual = mockMvcTester.post().uri("/game/move/up").exchange();
        
        // then
        assertThat(actual)
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo(expected);
    }
    
    @Test
    @DisplayName("Should include a hint into a JSON with a board")
    void shouldAddHintToBoardJson() {
        // given
        int[][] board = {
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0}
        };
        Game givenGame = new Game(board, new InProgress("Carry on."), 0, "Hint.");
        given(boardService.getHint()).willReturn(givenGame);
        String expected = objectMapper.writeValueAsString(givenGame);
        
        // when
        MvcTestResult actual = mockMvcTester.get().uri("/game/hint").exchange();
        
        // then
        assertThat(actual)
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo(expected);
    }
}