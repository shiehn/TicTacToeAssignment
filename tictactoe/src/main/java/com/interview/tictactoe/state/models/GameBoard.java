package com.interview.tictactoe.state.models;

import java.io.Serializable;
import java.util.ArrayList;
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

    public HashMap<String, String> update(String player, String position) throws RuntimeException {
        if(!supportedMoves.contains(position.toLowerCase())){
            throw new RuntimeException("unsupported location");
        }

        if(this.getBoard().get(position) != null) {
            return this.getBoard();
        }

        this.getBoard().put(position, player);

        return this.getBoard();
    }

    public String checkForWinner(){
        for(List<String> combos : getWinningCombos()){

            String winner = getBoard().get(combos.get(0));
                if (getBoard().get(combos.get(0)) != null &&
                        getBoard().get(combos.get(1)) != null &&
                        getBoard().get(combos.get(2)) != null) {

                    if (getBoard().get(combos.get(0)).equalsIgnoreCase(winner) &&
                            getBoard().get(combos.get(1)).equalsIgnoreCase(winner) &&
                            getBoard().get(combos.get(2)).equalsIgnoreCase(winner)) {
                        return winner;
                    }
                }
        }

        return null;
    }

    private List<List<String>> getWinningCombos(){
        List<List<String>> winningCombos = new ArrayList<>();

        List<String> rowA = Arrays.asList("a0","a1","a2");
        List<String> rowB = Arrays.asList("b0","b1","b2");
        List<String> rowC = Arrays.asList("c0","c1","c2");

        List<String> col1 = Arrays.asList("a0","b0","c0");
        List<String> col2 = Arrays.asList("a1","b1","c1");
        List<String> col3 = Arrays.asList("a2","b2","c2");

        List<String> diagonal1 = Arrays.asList("a0","b1","c2");
        List<String> diagonal2 = Arrays.asList("a2","b1","c0");

        winningCombos.add(rowA);
        winningCombos.add(rowB);
        winningCombos.add(rowC);

        winningCombos.add(col1);
        winningCombos.add(col2);
        winningCombos.add(col3);

        winningCombos.add(diagonal1);
        winningCombos.add(diagonal2);

        return winningCombos;
    }
}
