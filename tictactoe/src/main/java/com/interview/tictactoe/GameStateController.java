package com.interview.tictactoe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameStateController {

    @PostMapping("/gamestate/init")
    @ResponseBody
    public GameState init(@RequestParam(name="playera", required=true) String playerA, @RequestParam(name="playerb", required=true) String playerB) {

        // ACCEPTS
        // player A
        // player B

        //create users

        //RETURNS NEW GAME STATE

        GameState gameState = new GameState(playerA, playerB);

        S = new Student(
                "Eng2015001", "John Doe", Student.Gender.MALE, 1);

        Student retrievedStudent =
                studentRepository.findById("Eng2015001").get();

        return gameState;
    }

    @GetMapping("/gamestate/")
    @ResponseBody
    public GameState post(@RequestParam(name="playerA", required=true) String playerA) {
        // ACCEPTS
        // gamestate

        // validate
        // game exists
        // game is not complete
        // users are correct

        // move is valid

        //check for winner

        GameState gameState = new GameState("TOM", "JOE");
        return gameState;
    }

    @GetMapping("/gamestate")
    @ResponseBody
    public GameState get(@RequestParam(name="id", required=true) String id) {
        GameState gameState = new GameState("TOM", "JOE");
        return gameState;
    }
}
