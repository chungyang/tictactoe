package model;

/**
 * Created by chungyang on 2/18/20.
 */
public enum Player {
    PLAYER_1("X"), PLAYER_2("O");

    private final String mark;

    Player(String mark){
        this.mark = mark;
    }

    public String getMark(){
        return this.mark;
    }
}
