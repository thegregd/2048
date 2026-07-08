package com.greg._2048;

import com.greg._2048.command.MoveRight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveRightTest {

    private final MoveRight moveRight = new MoveRight();

    @Test
    @DisplayName("Move two tiles to the right edge")
    void shouldMoveTwoTilesToTheRightEdge() {
        // given
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 4, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 4}};

        // when
        moveRight.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Should move multiple tiles to the right edge")
    void shouldMoveTilesToTheRightEdge() {
        // given
        int[][] board = {
                {0, 2, 3, 4},
                {10, 0, 30, 40},
                {100, 200, 0, 400},
                {1000, 2000, 3000, 0}};
        int[][] expected = {
                {0, 2, 3, 4},
                {0, 10, 30, 40},
                {0, 100, 200, 400},
                {0, 1000, 2000, 3000}};

        // when
        moveRight.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Should move multiple tiles with consecutive zeros to the right edge")
    void shouldMoveTilesWithZerosToTheRightEdge() {
        // given
        int[][] board = {
                {0, 2, 3, 0},
                {30, 0, 0, 40},
                {0, 0, 300, 400},
                {1000, 2000, 0, 0}};
        int[][] expected = {
                {0, 0, 2, 3},
                {0, 0, 30, 40},
                {0, 0, 300, 400},
                {0, 0, 1000, 2000}};

        // when
        moveRight.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Should sum same tiles")
    void shouldSumSameTiles() {
        // given
        int[][] board = {
                {2, 2, 2, 2},
                {2, 2, 2, 0},
                {2, 8, 4, 4},
                {4, 4, 4, 8}};
        int[][] expected = {
                {0, 0, 4, 4},
                {0, 0, 2, 4},
                {0, 2, 8, 8},
                {0, 4, 8, 8}};

        // when
        moveRight.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }
}
