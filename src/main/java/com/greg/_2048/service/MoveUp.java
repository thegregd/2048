package com.greg._2048.service;

import org.springframework.stereotype.Component;

@Component("up")
public record MoveUp() implements Move<int[][]> {
    @Override
    public int execute(int[][] board) {
        int[] curCol = new int[4];
        int score = 0;
        
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                curCol[col] = board[col][row];
            }
            shiftArrayUpLeft(curCol, 0);
            evalBoardVert(board, row, curCol);
            
            score += sumSameTilesUpLeft(curCol);
            evalBoardVert(board, row, curCol);
            
            shiftArrayUpLeft(curCol, 0);
            evalBoardVert(board, row, curCol);
        }
        
        return score;
    }
}
