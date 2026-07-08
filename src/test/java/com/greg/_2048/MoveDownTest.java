package com.greg._2048;

import com.greg._2048.command.MoveDown;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoveDownTest {
    private final MoveDown moveDown = new MoveDown();

    @Test
    @DisplayName("Move two tiles to the bottom")
    void shouldMoveTwoTilesToTheBottom() {
        // given
        int[][] board = {
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 2, 0, 0}};

        // when
        prettyArrayPrint(board);
        moveDown.execute(board);
        System.out.println();
        prettyArrayPrint(board);
        System.out.println();

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    @Test
    @DisplayName("Move multiple tiles to the bottom")
    void shouldMoveMultipleTilesToTheBottom() {
        // given
        int[][] board = {
                {0, 2, 3, 4},
                {10, 0, 30, 40},
                {100, 200, 0, 400},
                {1000, 2000, 3000, 0}};
        int[][] expected = {
                {0, 0, 0, 0},
                {10, 2, 3, 4},
                {100, 200, 30, 40},
                {1000, 2000, 3000, 400}};

        // when
        prettyArrayPrint(board);
        moveDown.execute(board);
        System.out.println();
        prettyArrayPrint(board);

        // then
        assertThat(board).isDeepEqualTo(expected);

    }

    @Test
    @DisplayName("Should move multiple tiles with consecutive zeros to the bottom")
    void shouldMoveTilesWithZerosToTheBottom() {
        // given
        int[][] board = {
                {0, 0, 3, 4},
                {0, 20, 0, 40},
                {100, 0, 0, 400},
                {1000, 2000, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {100, 20, 0, 40},
                {1000, 2000, 3, 400}};

        // when
        moveDown.execute(board);

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
                {0, 0, 0, 0},
                {0, 0, 8, 8},
                {4, 4, 8, 4},
                {4, 4, 2, 8}};

        // when
        moveDown.execute(board);

        // then
        assertThat(board).isDeepEqualTo(expected);
    }

    private void prettyArrayPrint(int[][] arr) {
        for (final int[] ints : arr) {
            for (final int anInt : ints) {
                System.out.printf("%5d", anInt);
            }
            System.out.println();
        }
    }

}