package com.greg._2048.command;

import org.springframework.stereotype.Component;

@Component("left")
public record MoveLeft() implements Move<int[][]> {
    @Override
    public int execute(int[][] board) {
        int[] curCol = new int[4];
        int score = 0;
        
        for (int row = 0; row < board.length; row++) {
            System.arraycopy(board[row], 0, curCol, 0, board[row].length);
            
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
