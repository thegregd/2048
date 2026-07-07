package com.greg._2048.converter;

import com.greg._2048.model.Direction;
import com.greg._2048.service.Move;
import com.greg._2048.service.MoveDown;
import com.greg._2048.service.MoveLeft;
import com.greg._2048.service.MoveRight;
import com.greg._2048.service.MoveUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionConverterTest {
    
    private DirectionConverter directionConverter;
    
    @BeforeEach
    void setUp() {
        MoveUp moveUp = new MoveUp();
        MoveRight moveRight = new MoveRight();
        MoveDown moveDown = new MoveDown();
        MoveLeft moveLeft = new MoveLeft();
        
        directionConverter = new DirectionConverter(moveUp, moveRight, moveDown, moveLeft);
    }
    
    @Test
    @DisplayName("Should convert the string 'up' to Direction.UP")
    void shouldConvertUpStringToDirectionUp() {
        // when
        Direction direction = directionConverter.convert("up");
        
        // then
        assertThat(direction).isEqualTo(Direction.UP);
    }
    
    @Test
    @DisplayName("Should convert the string 'right' to Direction.RIGHT")
    void shouldConvertRightStringToDirectionRight() {
        // when
        Direction direction = directionConverter.convert("right");
        
        // then
        assertThat(direction).isEqualTo(Direction.RIGHT);
    }
    
    @Test
    @DisplayName("Should convert the string 'down' to Direction.DOWN")
    void shouldConvertDownStringToDirectionDown() {
        // when
        Direction direction = directionConverter.convert("down");
        
        // then
        assertThat(direction).isEqualTo(Direction.DOWN);
    }
    
    @Test
    @DisplayName("Should convert the string 'left' to Direction.LEFT")
    void shouldConvertLeftStringToDirectionLeft() {
        // when
        Direction direction = directionConverter.convert("left");
        
        // then
        assertThat(direction).isEqualTo(Direction.LEFT);
    }
    
    @Test
    @DisplayName("Should convert Direction.UP to MoveUp")
    void shouldConvertDirectionUpToMoveUp() {
        // when
        Move<int[][]> move = directionConverter.convert(Direction.UP);
        
        // then
        assertThat(move).isInstanceOf(MoveUp.class);
    }
    
    @Test
    @DisplayName("Should convert Direction.RIGHT to MoveRight")
    void shouldConvertDirectionRightToMoveRight() {
        // when
        Move<int[][]> move = directionConverter.convert(Direction.RIGHT);
        
        // then
        assertThat(move).isInstanceOf(MoveRight.class);
    }
    
    @Test
    @DisplayName("Should convert Direction.DOWN to MoveDown")
    void shouldConvertDirectionDownToMoveDown() {
        // when
        Move<int[][]> move = directionConverter.convert(Direction.DOWN);
        
        // then
        assertThat(move).isInstanceOf(MoveDown.class);
    }
    
    @Test
    @DisplayName("Should convert Direction.LEFT to MoveLeft")
    void shouldConvertDirectionLeftToMoveLeft() {
        // when
        Move<int[][]> move = directionConverter.convert(Direction.LEFT);
        
        // then
        assertThat(move).isInstanceOf(MoveLeft.class);
    }
}