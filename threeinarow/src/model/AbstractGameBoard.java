package model;

/**
 * Created by chungyang on 2/26/20.
 */
public abstract class AbstractGameBoard {

    public abstract void reset();

    /**
     * Place a marker at the specified block. Return false if it doesn't result in a win,
     * return true otherwise.
     */
    public abstract boolean placeMarker(int row, int col, String marker);


    public abstract boolean isLegalMove(int row, int col);

    public abstract String getBlockContent(int row, int col);

    public abstract int getMovesLeft();
}
