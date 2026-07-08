package com.greg._2048.service;

import com.greg._2048.command.MoveDown;
import com.greg._2048.command.MoveLeft;
import com.greg._2048.command.MoveRight;
import com.greg._2048.command.MoveUp;
import com.greg._2048.model.Game;
import com.greg._2048.model.InProgress;
import com.greg._2048.model.Status.Referee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MessageSource.class})
class BoardServiceTest {
    
    private BoardService boardService;
    
    @MockitoBean
    private MessageSource messageSource;
    
    @BeforeEach
    void setUp() {
        Referee referee = new Referee(messageSource);
        
        Random random = new Random();
        
        boardService = new BoardService(referee, messageSource, null, new ArrayList<>(), random);
        boardService.createNewBoard();
    }
    
    @Test
    @DisplayName("Should move tiles UP and update score")
    void shouldMoveUp() {
        // when
        Game game = boardService.move(new MoveUp());
        
        // then
        assertThat(game).isNotNull();
        assertThat(game.board()).isNotEmpty();
        assertThat(game.score()).isGreaterThanOrEqualTo(0);
        assertThat(game.status()).isInstanceOf(InProgress.class);
    }
    
    @Test
    @DisplayName("Should move tiles RIGHT and update score")
    void shouldMoveRight() {
        // when
        Game game = boardService.move(new MoveRight());
        
        // then
        assertThat(game).isNotNull();
        assertThat(game.board()).isNotEmpty();
        assertThat(game.score()).isGreaterThanOrEqualTo(0);
        assertThat(game.status()).isInstanceOf(InProgress.class);
    }
    
    @Test
    @DisplayName("Should move tiles DOWN and update score")
    void shouldMoveDown() {
        // when
        Game game = boardService.move(new MoveDown());
        
        // then
        assertThat(game).isNotNull();
        assertThat(game.board()).isNotEmpty();
        assertThat(game.score()).isGreaterThanOrEqualTo(0);
        assertThat(game.status()).isInstanceOf(InProgress.class);
    }
    
    @Test
    @DisplayName("Should move tiles LEFT and update score")
    void shouldMoveLeft() {
        // when
        Game game = boardService.move(new MoveLeft());
        
        // then
        assertThat(game).isNotNull();
        assertThat(game.board()).isNotEmpty();
        assertThat(game.score()).isGreaterThanOrEqualTo(0);
        assertThat(game.status()).isInstanceOf(InProgress.class);
    }
}