package com.greg._2048.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.SIMPLE_NAME, include = As.PROPERTY, property = "type")
public sealed interface Status permits Win, Lose, InProgress {
    String message();
    
    @Component
    class Referee {
        private final static int WINNIG_VALUE = 2048;
        private final static int MOVE_POSSIBLE = 0;
        
        private final String WIN_MESSAGE;
        private final String LOSE_MESSAGE;
        private final String IN_PROGRESS_MESSAGE;
        
        public Referee(final MessageSource messageSource) {
            WIN_MESSAGE = messageSource.getMessage("game.won", null, LocaleContextHolder.getLocale());
            LOSE_MESSAGE = messageSource.getMessage("game.lost", null, LocaleContextHolder.getLocale());
            IN_PROGRESS_MESSAGE = messageSource.getMessage("game.in_progress", null, LocaleContextHolder.getLocale());
        }
        
        public Status check(final int[][] board) {
            final boolean win = Arrays.stream(board)
                    .flatMapToInt(Arrays::stream)
                    .anyMatch(tile -> tile >= WINNIG_VALUE);
            
            final boolean canMove = Arrays.stream(board)
                    .flatMapToInt(Arrays::stream)
                    .anyMatch(tile -> tile == MOVE_POSSIBLE);
            
            return win ? new Win(WIN_MESSAGE) : canMove ? new InProgress(IN_PROGRESS_MESSAGE) : new Lose(LOSE_MESSAGE);
        }
    }
}