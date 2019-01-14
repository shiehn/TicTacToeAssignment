package com.interview.tictactoe.state;

import com.interview.tictactoe.dal.GameStateService;
import com.interview.tictactoe.state.models.GameInitRequest;
import com.interview.tictactoe.state.models.GameMoveRequest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameStateControllerTest {

    @Test
    public void shouldReturnErrorIfNamesNotProvided(){
        GameStateController gameStateController = new GameStateController(null);

        GameInitRequest gameInitRequest = new GameInitRequest();
        gameInitRequest.setPlayera("playerA");

        GameState gameState = gameStateController.init(gameInitRequest);

        assertThat(gameState.getError()).isNotNull();
        assertThat(gameState.getError()).isEqualToIgnoringCase("please provide player names");
    }

    @Test
    public void shouldReturnErrorMessageIfGameNotFound(){
        GameStateService mockedService = mock(GameStateService.class);

        when(mockedService.findById(any())).thenReturn(null);

        GameStateController gameStateController = new GameStateController(mockedService);

        GameMoveRequest gameMoveRequest = new GameMoveRequest("bogusId", "playerA", "c0");
        GameState gameState = gameStateController.move(gameMoveRequest);

        assertThat(gameState.getError()).isNotNull();
        assertThat(gameState.getError()).isEqualToIgnoringCase("game not found");
    }

    @Test
    public void shouldReturnErrorMessageIfGameCompleted(){
        GameStateService mockedService = mock(GameStateService.class);

        GameState gameStateMock = new GameState("playerA", "playerB");
        gameStateMock.setCompleted(true);
        when(mockedService.findById(any())).thenReturn(gameStateMock);

        GameStateController gameStateController = new GameStateController(mockedService);

        GameMoveRequest gameMoveRequest = new GameMoveRequest("bogusId", "playerA", "c0");
        GameState gameState = gameStateController.move(gameMoveRequest);

        assertThat(gameState.getError()).isNotNull();
        assertThat(gameState.getError()).isEqualToIgnoringCase("game completed");
    }

    @Test
    public void shouldReturnErrorIfMoveIsInvalid(){

    }

    @Test
    public void shouldReturnErrorMessageIfWrongPlayerMoved(){
        GameStateService mockedService = mock(GameStateService.class);

        GameState gameStateMock = new GameState("playerA", "playerB");
        gameStateMock.setMove("playerB");
        when(mockedService.findById(any())).thenReturn(gameStateMock);

        GameStateController gameStateController = new GameStateController(mockedService);

        GameMoveRequest gameMoveRequest = new GameMoveRequest("bogusId", "playerA", "c0");
        GameState gameState = gameStateController.move(gameMoveRequest);

        assertThat(gameState.getError()).isNotNull();
        assertThat(gameState.getError()).isEqualToIgnoringCase("its playerB's turn");
    }
}