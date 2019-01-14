package com.interview.tictactoe.controllers;

import com.interview.tictactoe.dal.GameStateService;
import com.interview.tictactoe.GameState;
import com.interview.tictactoe.identity.Identity;
import com.interview.tictactoe.models.GameInitRequest;
import com.interview.tictactoe.models.GameMoveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameStateController {

    GameStateService gameStateService;
    Identity identity;

    @Autowired
    public GameStateController(GameStateService gameStateService, Identity identity){
        this.gameStateService = gameStateService;
        this.identity = identity;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/gamestate/init")
    @ResponseBody
    public GameState init(@RequestBody GameInitRequest gameInitRequest){
        identity.validateToken("placeholder-token");

        GameState gameState = new GameState(
                gameInitRequest.getPlayera(), gameInitRequest.getPlayerb());

        if(gameInitRequest.getPlayera().equalsIgnoreCase(gameInitRequest.getPlayerb())){
            gameState.setError("please provide different names");
            return gameState;
        }

        if(gameInitRequest.getPlayera() == null || gameInitRequest.getPlayera().isEmpty() || gameInitRequest.getPlayerb() == null || gameInitRequest.getPlayerb().isEmpty()){
            gameState.setError("please provide player names");
            return gameState;
        }

        gameStateService.save(gameState);

        GameState retrieved = gameStateService.findById(gameState.getGameId());

        return retrieved;
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/gamestate/move")
    @ResponseBody
    public GameState move(@RequestBody GameMoveRequest gameMoveRequest) {
        identity.validateToken("placeholder-token");

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

        String gameResult = retrievedState.getBoard().checkForTieOrWinner();
        if(gameResult != null){
            if(gameResult.equalsIgnoreCase("tie")){
                retrievedState.setWinner("Tie Game!");
            } else {
                retrievedState.setWinner(gameResult);
            }

            retrievedState.setCompleted(true);
        }

        this.gameStateService.save(retrievedState);

        return retrievedState;
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/gamestate/{id}")
    @ResponseBody
    public GameState get(@PathVariable(name="id", required=true) String id) {
        identity.validateToken("placeholder-token");

        GameState retrieved = gameStateService.findById(id);
        return retrieved;
    }
}
