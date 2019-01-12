package com.interview.tictactoe.dal;

import com.interview.tictactoe.state.GameState;
import org.springframework.data.repository.CrudRepository;

public interface GameStateRepository extends CrudRepository<GameState, String> {}
