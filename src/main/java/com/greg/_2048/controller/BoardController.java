package com.greg._2048.controller;

import com.greg._2048.api.GameApi;
import com.greg._2048.converter.DirectionConverter;
import com.greg._2048.model.Direction;
import com.greg._2048.model.Game;
import com.greg._2048.service.BoardService;
import com.greg._2048.command.Move;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController implements GameApi {
    
    private final BoardService boardService;
    private final DirectionConverter directionConverter;
    
    @Override
    public ResponseEntity<Game> startGame() {
        return ResponseEntity.ofNullable(boardService.createNewBoard());
    }
    
    @Override
    public ResponseEntity<Game> moveTiles(final Direction direction) {
        Move<int[][]> move = directionConverter.convert(direction);
        
        return ResponseEntity.ofNullable(boardService.move(move));
    }
    
    @Override
    public ResponseEntity<Game> getHint() {
        return ResponseEntity.ofNullable(boardService.getHint());
    }
}
