package com.interview.tictactoe.state;

import com.interview.tictactoe.state.models.GameBoard;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("GameState")
public class GameState implements Serializable {
    private String id;
    private String playerA;
    private String playerB;
    private String move;
    private boolean completed;
    private String winner;
    private String error;
    private GameBoard board;

    public GameState(String playerA, String playerB) {
        this.id = UUID.randomUUID().toString();
        this.playerA = playerA;
        this.playerB = playerB;

        this.setMove(playerA);
        this.initalizeBoard();
    }

    private void initalizeBoard(){
        board = new GameBoard();
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

    public GameBoard getBoard() {
        return board;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
