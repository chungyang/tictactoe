package model;

/**
 * Created by chungyang on 2/18/20.
 */
public enum Player {
    PLAYER_1("X", 1), PLAYER_2("O", 2);

    private final String mark;
    private final int id;

    Player(String mark, int id){
        this.mark = mark;
        this.id = id;
    }

    public String getMark(){
        return this.mark;
    }

    public int getId(){
        return this.id;
    }
}
