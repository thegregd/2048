package com.greg._2048.service;

import com.greg._2048.command.Move;
import com.greg._2048.model.Game;
import com.greg._2048.model.InProgress;
import com.greg._2048.model.Pair;
import com.greg._2048.model.Status;
import com.greg._2048.model.Status.Referee;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BoardService {
    private static final int DEFAULT_BOARD_SIZE = 4;
    
    private final Referee referee;
    
    private final MessageSource messageSource;
    
    private final HintService hintService;
    
    private final List<int[][]> previousMoves;
    
    private final Random rand;
    
    private int[][] board;
    private int score = 0;
    private boolean hinted = false;
    
    public Game createNewBoard() {
        board = new int[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
        
        zeroTheBoard(board, 0);
        score = 0;
        
        drawTile(board, rand);
        drawTile(board, rand);
        
        previousMoves.add(copyArray(board));
        
        String newGameMessage = messageSource.getMessage("game.new_game", null, LocaleContextHolder.getLocale());
        return new Game(board, new InProgress(newGameMessage), score, "");
    }
    
    public Game move(final Move<int[][]> direction) {
        hinted = false;
        final int[][] newBoard = copyArray(board);
        
        score += direction.execute(newBoard);
        
        Status status = referee.check(newBoard);
        if (status instanceof InProgress) {
            drawTile(newBoard, rand);
        }
        
        previousMoves.add(newBoard);
        board = newBoard;
        
        return new Game(newBoard, status, score, "");
    }
    
    public Game getHint() {
        if (!hinted) {
            hinted = true;
            return new Game(board, new Status.Referee(messageSource).check(board), score, hintService.getHint(board));
        } else {
            return new Game(board, new Status.Referee(messageSource).check(board), score, hintService.getLastHint());
        }
    }
    
    private void drawTile(final int[][] board, final Random rand) {
        int tileToPut = this.randomTile(rand);
        
        ArrayList<Pair> emptyTiles = this.findEmptyTiles(board);
        
        if (!emptyTiles.isEmpty()) {
            int randomTile = rand.nextInt(emptyTiles.size());
            Pair emptyTile = emptyTiles.remove(randomTile);
            board[emptyTile.x()][emptyTile.y()] = tileToPut;
        }
    }
    
    private void zeroTheBoard(final int[][] board, final int value) {
        for (final int[] ints : board) {
            Arrays.fill(ints, value);
        }
    }
    
    private int randomTile(final Random rand) {
        final int[] tiles = {2, 2, 2, 4, 4};
        
        int tileIdx = rand.nextInt(5);
        
        return tiles[tileIdx];
    }
    
    private ArrayList<Pair> findEmptyTiles(final int[][] board) {
        ArrayList<Pair> emptyTiles = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    emptyTiles.add(new Pair(row, col));
                }
            }
        }
        
        return emptyTiles;
    }
    
    private int[][] copyArray(final int[][] old) {
        final int[][] newArr = new int[old.length][old[0].length];
        
        for (int row = 0; row < old.length; row++) {
            System.arraycopy(old[row], 0, newArr[row], 0, old[row].length);
        }
        
        return newArr;
    }
}
