package com.interview.tictactoe.state;

import com.interview.tictactoe.dal.GameStateService;
import com.interview.tictactoe.state.models.GameInitRequest;
import com.interview.tictactoe.state.models.GameMoveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameStateController {

    GameStateService gameStateService;

    @Autowired
    public GameStateController(GameStateService gameStateService){
        this.gameStateService = gameStateService;
    }

    @PostMapping("/gamestate/init")
    @ResponseBody
    public GameState init(@RequestBody GameInitRequest gameInitRequest){
        GameState gameState = new GameState(
                gameInitRequest.getPlayera(), gameInitRequest.getPlayerb());

        if(gameInitRequest.getPlayera() == null || gameInitRequest.getPlayera().isEmpty() || gameInitRequest.getPlayerb() == null || gameInitRequest.getPlayerb().isEmpty()){
            gameState.setError("please provide player names");
            return gameState;
        }

        gameStateService.save(gameState);

        GameState retrieved = gameStateService.findById(gameState.getGameId());

        return retrieved;
    }

    @PostMapping("/gamestate/move")
    @ResponseBody
    public GameState move(@RequestBody GameMoveRequest gameMoveRequest) {
        GameState retrievedState = gameStateService.findById(gameMoveRequest.getId());

        if(retrievedState == null){
            GameState gameState = new GameState("", "");
            gameState.setError("game not found");
            return gameState;
        }

        if(retrievedState.isCompleted()){
            retrievedState.setError("game completed");
            return retrievedState;
        }

        if(!retrievedState.getMove().equalsIgnoreCase(gameMoveRequest.getPlayer())){
            retrievedState.setError("its " + retrievedState.getMove() + "'s turn");
            return retrievedState;
        }

        try {
            retrievedState.getBoard().update(gameMoveRequest.getPlayer(), gameMoveRequest.getPosition());
            if(retrievedState.getPlayerA().equalsIgnoreCase(retrievedState.getMove())){
                retrievedState.setMove(retrievedState.getPlayerB());
            }else{
                retrievedState.setMove(retrievedState.getPlayerA());
            }
        }catch (Exception e){
            retrievedState.setError(e.getMessage());
            return retrievedState;
        }

        String winner = retrievedState.getBoard().checkForWinner();
        if(winner != null){
            retrievedState.setWinner(winner);
            retrievedState.setCompleted(true);
        }

        this.gameStateService.save(retrievedState);

        return retrievedState;
    }

    @GetMapping("/gamestate/{id}")
    @ResponseBody
    public GameState get(@PathVariable(name="id", required=true) String id) {
        GameState retrieved = gameStateService.findById(id);
        return retrieved;
    }
}
