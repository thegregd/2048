package com.greg._2048.converter;

import com.greg._2048.model.Direction;
import com.greg._2048.command.Move;
import com.greg._2048.command.MoveDown;
import com.greg._2048.command.MoveLeft;
import com.greg._2048.command.MoveRight;
import com.greg._2048.command.MoveUp;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DirectionConverter implements Converter<String, Direction> {
    private final MoveUp up;
    private final MoveRight right;
    private final MoveDown down;
    private final MoveLeft left;
    
    @Override
    public Direction convert(final String source) {
        return Direction.fromValue(source.toLowerCase());
    }
    
    public Move<int[][]> convert(final Direction direction) {
        return switch (direction) {
            case UP -> up;
            case RIGHT -> right;
            case DOWN -> down;
            case LEFT -> left;
        };
    }
}
