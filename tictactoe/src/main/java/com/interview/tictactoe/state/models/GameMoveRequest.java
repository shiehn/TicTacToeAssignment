package com.interview.tictactoe.state.models;

public class GameMoveRequest {
    private String id;
    private String player;
    private String position;

    public GameMoveRequest(String id, String player, String position){
        this.id = id;
        this.player = player;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
