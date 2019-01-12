package com.interview.tictactoe.state;

import com.interview.tictactoe.dal.GameStateService;
import com.interview.tictactoe.state.models.GameInitRequest;
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

    // ACCEPTS
        // player A
        // player B

        //create users

        //RETURNS NEW GAME STATE
        GameState gameState = new GameState(
                gameInitRequest.getPlayera(), gameInitRequest.getPlayerb());
        gameStateService.save(gameState);

        GameState retrieved = gameStateService.findById(gameState.getGameId());

        return retrieved;
    }

    @GetMapping("/gamestate/")
    @ResponseBody
    public GameState post(@RequestAttribute(name="id", required=true) String id) {
        // ACCEPTS
        // gamestate

        // validate
        // game exists
        // game is not complete
        // users are correct

        // move is valid

        //check for winner

        GameState retrieved = gameStateService.findById(id);

        return retrieved;
    }

    @GetMapping("/gamestate/{id}")
    @ResponseBody
    public GameState get(@PathVariable(name="id", required=true) String id) {
        GameState retrieved = gameStateService.findById(id);
        return retrieved;
    }
}
