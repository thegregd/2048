package com.greg._2048.service;

//@FunctionalInterface
public sealed interface Move<I> permits MoveUp, MoveRight, MoveDown, MoveLeft {
    int execute(I arg);
    
    default void evalBoardVert(final int[][] board, final int row, final int[] curCol) {
        for (int col = 0; col < board[row].length; col++) {
            board[col][row] = curCol[col];
        }
    }
    
    default void evalBoardHoriz(final int[][] board, final int row, final int[] curCol) {
        for (int col = 0; col < board[row].length; col++) {
            board[row][col] = curCol[col];
        }
    }
    
    default int sumSameTilesUpLeft(final int[] arr) {
        int sum = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i - 1] == arr[i]) {
                arr[i - 1] = arr[i - 1] + arr[i];
                sum += arr[i - 1];
                arr[i] = 0;
            }
        }
        
        return sum;
    }
    
    default int sumSameTilesDownRight(final int[] arr) {
        int sum = 0;
        for (int i = arr.length - 2; i >= 0; --i) {
            if (arr[i + 1] == arr[i]) {
                arr[i + 1] = arr[i + 1] + arr[i];
                sum += arr[i + 1];
                arr[i] = 0;
            }
        }
        
        return sum;
    }
    
    default void shiftArrayDownRight(final int[] arr, int idx) {
        if (idx == arr.length)
            return;
        if (idx != 0) {
            if (arr[idx] == 0) {
                if (arr[idx - 1] != 0) {
                    arr[idx] = arr[idx - 1];
                    arr[idx - 1] = 0;
                    shiftArrayDownRight(arr, --idx);
                }
            }
        }
        shiftArrayDownRight(arr, ++idx);
    }
    
    default void shiftArrayUpLeft(final int[] arr, int idx) {
        if (idx == arr.length)
            return;
        if (arr[idx] == 0) {
            shiftArrayUpLeft(arr, ++idx);
        } else {
            if (idx != 0) {
                if (arr[idx - 1] == 0) {
                    arr[idx - 1] = arr[idx];
                    arr[idx] = 0;
                    shiftArrayUpLeft(arr, --idx);
                }
            }
            shiftArrayUpLeft(arr, ++idx);
        }
    }
}
