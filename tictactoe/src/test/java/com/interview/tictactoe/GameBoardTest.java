package com.interview.tictactoe;

import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class GameBoardTest {

    GameBoard board;

    public void setup(){
        board = new GameBoard();
    }

    @Test(expected = RuntimeException.class)
    public void throwsErrorWhenPositionNotSupported(){
        setup();

        board.update("fake", "bad position");
    }

    @Test
    public void updatesBoardWhenPositionIsValid(){
        setup();

        assertThat(board.getBoard().get("a2")).isNull();

        HashMap<String, String> updatedBoard = board.update("playerA", "a2");
        assertThat(updatedBoard.get("a2")).isNotNull();
        assertThat(updatedBoard.get("a2")).isEqualToIgnoringCase("playerA");
    }

    @Test(expected = RuntimeException.class)
    public void doesNotUpdateBoardWhenPositionAlreadyTaken(){
        setup();

        assertThat(board.getBoard().put("a2", "playerB")).isNull();

        HashMap<String, String> updatedBoard = board.update("playerA", "a2");
    }

    @Test
    public void returnsNullWhenNoWinner(){
        setup();

        board.getBoard().put("c0", "playerA");
        board.getBoard().put("c1", "playerB");
        board.getBoard().put("c2", "playerB");

        assertThat(this.board.checkForTieOrWinner()).isNull();
    }

    @Test
    public void returnsWinnerDiagonal(){
        setup();

        board.getBoard().put("c0", "playerA");
        board.getBoard().put("b1", "playerA");
        board.getBoard().put("a2", "playerA");

        board.getBoard().put("c1", "otherPlayer");

        assertThat(this.board.checkForTieOrWinner()).isEqualToIgnoringCase("playerA");
    }

    @Test
    public void returnsWinnerRow(){
        setup();

        board.getBoard().put("b0", "playerB");
        board.getBoard().put("b1", "playerB");
        board.getBoard().put("b2", "playerB");

        board.getBoard().put("c1", "otherPlayer");

        assertThat(this.board.checkForTieOrWinner()).isEqualToIgnoringCase("playerB");
    }

    @Test
    public void returnsWinnerCol(){
        setup();

        board.getBoard().put("c0", "playerC");
        board.getBoard().put("c1", "playerC");
        board.getBoard().put("c2", "playerC");

        board.getBoard().put("a1", "otherPlayer");

        assertThat(this.board.checkForTieOrWinner()).isEqualToIgnoringCase("playerC");
    }

    @Test
    public void returnsTie(){
        setup();

        board.getBoard().put("a0", "playerA");
        board.getBoard().put("a1", "playerC");
        board.getBoard().put("a2", "playerA");

        board.getBoard().put("b0", "playerA");
        board.getBoard().put("b1", "playerC");
        board.getBoard().put("b2", "playerC");

        board.getBoard().put("c0", "playerC");
        board.getBoard().put("c1", "playerA");
        board.getBoard().put("c2", "playerA");

        assertThat(this.board.checkForTieOrWinner()).isEqualToIgnoringCase("tie");
    }
}