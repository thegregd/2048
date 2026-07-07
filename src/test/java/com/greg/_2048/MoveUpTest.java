package com.greg._2048;

import static org.assertj.core.api.Assertions.assertThat;

import com.greg._2048.service.MoveUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoveUpTest {

    private final MoveUp moveUp = new MoveUp();

    @Test
    @DisplayName("Move two tiles to the top")
    void shouldMoveTwoTilesToTheTop() {
        // given
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 4}};
        int[][] expected = {
                {0, 2, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};

        // when
        moveUp.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Should move multiple tiles to the top")
    void shouldMoveTilesToTheTop() {
        // given
        int[][] board = {
                {0, 2, 3, 4},
                {10, 0, 30, 40},
                {100, 200, 0, 400},
                {1000, 2000, 3000, 0}};
        int[][] expected = {
                {10, 2, 3, 4},
                {100, 200, 30, 40},
                {1000, 2000, 3000, 400},
                {0, 0, 0, 0}};

        // when
        moveUp.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Should move multiple tiles with consecutive zeros to the top")
    void shouldMoveTilesWithZeorsToTheTop() {
        // given
        int[][] board = {
                {0, 2, 3, 4},
                {0, 0, 30, 40},
                {100, 200, 0, 400},
                {1000, 2000, 0, 0}};
        int[][] expected = {
                {100, 2, 3, 4},
                {1000, 200, 30, 40},
                {0, 2000, 0, 400},
                {0, 0, 0, 0}};

        // when
        moveUp.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Should sum same tiles")
    void shouldSumSameTiles() {
        // given
        int[][] board = {
                {2, 0, 4, 8},
                {2, 2, 4, 4},
                {2, 2, 8, 4},
                {2, 4, 2, 4}};
        int[][] expected = {
                {4, 4, 8, 8},
                {4, 4, 8, 8},
                {0, 0, 2, 4},
                {0, 0, 0, 0}};

        // when
        moveUp.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

}
