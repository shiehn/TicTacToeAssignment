package com.interview.tictactoe.dal;

import com.interview.tictactoe.state.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStateService {

    GameStateRepository gameStateRepository;

    @Autowired
    public GameStateService(GameStateRepository gameStateRepository){
        this.gameStateRepository = gameStateRepository;
    }

    public GameState findById(String id){
        return this.gameStateRepository.findById(id).get();
    }

    public GameState save(GameState gameState){
        return this.gameStateRepository.save(gameState);
    }
}
