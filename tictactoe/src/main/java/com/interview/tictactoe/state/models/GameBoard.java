package com.interview.tictactoe.state.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GameBoard implements Serializable {
    private HashMap<String, String> board;
    List<String> supportedMoves = Arrays.asList("a0","a1","a2","b0","b1","b2","c0","c1","c2");

    public GameBoard(){
        this.board = new HashMap<>();

        this.board.put("a0", null);
        this.board.put("a1", null);
        this.board.put("a2", null);
        this.board.put("b0", null);
        this.board.put("b1", null);
        this.board.put("b2", null);
        this.board.put("c0", null);
        this.board.put("c1", null);
        this.board.put("c2", null);
    }

    public HashMap<String, String> getBoard() {
        return this.board;
    }

    public HashMap<String, String> update(String player, String position){
        if(!supportedMoves.contains(position.toLowerCase())){
            throw new RuntimeException("unsupported location");
        }

        return getBoard();
    }
}
