package com.greg._2048.command;

import org.springframework.stereotype.Component;

@Component("right")
public record MoveRight() implements Move<int[][]> {
    @Override
    public int execute(int[][] board) {
        int[] curCol = new int[4];
        int score = 0;
        
        for (int row = 0; row < board.length; row++) {
            System.arraycopy(board[row], 0, curCol, 0, board[row].length);
            
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
