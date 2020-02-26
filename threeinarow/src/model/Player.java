package model;

/**
 * Created by chungyang on 2/18/20.
 */
public enum Player {
    PLAYER_1("X", 1), PLAYER_2("O", 2);

    private final String marker;
    private final int id;

    Player(String marker, int id){
        this.marker = marker;
        this.id = id;
    }

    public String getMarker(){
        return this.marker;
    }

    public int getId(){
        return this.id;
    }
}
