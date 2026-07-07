package com.greg._2048.service;

import org.springframework.stereotype.Component;

@Component("left")
public record MoveLeft() implements Move<int[][]> {
    @Override
    public int execute(int[][] board) {
        int[] curCol = new int[4];
        int score = 0;
        
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                curCol[col] = board[row][col];
            }
            
            shiftArrayUpLeft(curCol, 0);
            evalBoardHoriz(board, row, curCol);
            
            score += sumSameTilesUpLeft(curCol);
            evalBoardHoriz(board, row, curCol);
            
            shiftArrayUpLeft(curCol, 0);
            evalBoardHoriz(board, row, curCol);
        }
        
        return score;
    }
    
}
