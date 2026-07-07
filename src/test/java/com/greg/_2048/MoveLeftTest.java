package com.greg._2048;

import com.greg._2048.service.MoveLeft;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveLeftTest {

    private final MoveLeft moveLeft = new MoveLeft();

    @Test
    @DisplayName("Move two tiles to the left edge")
    void shouldMoveTwoTilesToTheLeftEdge() {
        // given
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 4}};
        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {4, 0, 0, 0}};

        // when
        moveLeft.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Should move multiple tiles to the left edge")
    void shouldMoveTilesToTheLeftEdge() {
        // given
        int[][] board = {
                {0, 2, 3, 4},
                {10, 0, 30, 40},
                {100, 200, 0, 400},
                {1000, 2000, 3000, 0}};
        int[][] expected = {
                {2, 3, 4, 0},
                {10, 30, 40, 0},
                {100, 200, 400, 0},
                {1000, 2000, 3000, 0}};

        // when
        moveLeft.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Should move multiple tiles with consecutive zeros to the left edge")
    void shouldMoveTilesWithZerosToTheLeftEdge() {
        // given
        int[][] board = {
                {0, 2, 3, 4},
                {0, 0, 30, 40},
                {100, 200, 0, 400},
                {1000, 2000, 0, 0}};
        int[][] expected = {
                {2, 3, 4, 0},
                {30, 40, 0, 0},
                {100, 200, 400, 0},
                {1000, 2000, 0, 0}};

        // when
        moveLeft.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Should sum same tiles")
    void shouldSumSameTiles() {
        // given
        int[][] board = {
                {2, 2, 2, 2},
                {0, 2, 2, 2},
                {4, 4, 8, 2},
                {8, 4, 4, 4}};
        int[][] expected = {
                {4, 4, 0, 0},
                {4, 2, 0, 0},
                {8, 8, 2, 0},
                {8, 8, 4, 0}};

        // when
        moveLeft.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }
}
