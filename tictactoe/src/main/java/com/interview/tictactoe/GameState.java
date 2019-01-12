package com.interview.tictactoe;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RedisHash("GameState")
public class GameState implements Serializable {
    private String id;
    private String playerA;
    private String playerB;
    private String move;
    private boolean completed;
    private List<List<String>> board;

    public GameState(String playerA, String playerB) {
        this.id = UUID.randomUUID().toString();
        this.playerA = playerA;
        this.playerB = playerB;

        this.initalizeBoard();
    }

    private void initalizeBoard(){
        List<String> rowA = new ArrayList<>();
        List<String> rowB = new ArrayList<>();
        List<String> rowC = new ArrayList<>();

        this.board = new ArrayList<>();
        this.board.add(rowA);
        this.board.add(rowB);
        this.board.add(rowC);
    }

    public String getGameId() {
        return id;
    }

    public void setGameId(String id) {
        this.id = id;
    }

    public String getPlayerA() {
        return playerA;
    }

    public void setPlayerA(String playerA) {
        this.playerA = playerA;
    }

    public String getPlayerB() {
        return playerB;
    }

    public void setPlayerB(String playerB) {
        this.playerB = playerB;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<List<String>> getBoard() {
        return board;
    }

    public void setBoard(List<List<String>> board) {
        this.board = board;
    }
}
