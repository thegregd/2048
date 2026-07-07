package com.greg._2048.service;

import com.greg._2048.service.Status.Referee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StatusTest {
    private Referee referee;
    
    @Mock
    private MessageSource messageSource;
    
    private final static String WIN_MESSAGE = "You won! 2048 reached";
    private final static String LOSE_MESSAGE = "No more moves.";
    private final static String IN_PROGRESS_MESSAGE = "Carry on.";
    
    @BeforeEach
    void setUp() {
        given(messageSource.getMessage("game.won", null, Locale.getDefault())).willReturn(WIN_MESSAGE);
        given(messageSource.getMessage("game.lost", null, Locale.getDefault())).willReturn(LOSE_MESSAGE);
        given(messageSource.getMessage("game.in_progress", null, Locale.getDefault())).willReturn(IN_PROGRESS_MESSAGE);
        referee = new Referee(messageSource);
    }

    @Test
    @DisplayName("Should show win if there is a tile with 2048 value")
    void shouldReturnWin() {
        
        String expectedMessage = "You won! 2048 reached";
        Status expected = new Win(expectedMessage);

        // given
        final int[][] board = {
                {0, 0, 0, 0},
                {2048, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        // when
        Status actual = referee.check(board);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}