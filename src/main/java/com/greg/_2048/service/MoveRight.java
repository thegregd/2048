package com.greg._2048.service;

import org.springframework.stereotype.Component;

@Component("right")
public record MoveRight() implements Move<int[][]> {
    @Override
    public int execute(int[][] board) {
        int[] curCol = new int[4];
        int score = 0;
        
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                curCol[col] = board[row][col];
            }
            
            shiftArrayDownRight(curCol, 0);
            evalBoardHoriz(board, row, curCol);
            
            score += sumSameTilesDownRight(curCol);
            evalBoardHoriz(board, row, curCol);
            
            shiftArrayDownRight(curCol, 0);
            evalBoardHoriz(board, row, curCol);
        }
        
        return score;
    }
}
