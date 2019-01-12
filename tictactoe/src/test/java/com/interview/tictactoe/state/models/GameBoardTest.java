package com.interview.tictactoe.state.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {

    GameBoard board;

    @Before
    public void setup(){
        board = new GameBoard();
    }

    @Test(expected = RuntimeException.class)
    public void throwsErrorWhenPositionNotSupported(){
        board.update("fake", "bad position");
    }

    public void returnsBoardWhenPositionUpdated(){
        board.update("fake", "bad position");
    }

    // must be correct players move


    // check for 8 winning combos
}