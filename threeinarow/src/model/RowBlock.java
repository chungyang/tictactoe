package model;

import controller.BoardController;
import view.View;

/**
 * Created by chungyang on 2/12/20.
 */
public abstract class RowBlock {

    /**
     * The game that contains this block
     */
    protected BoardController boardController;

    /**
     * The current value of the contents of this block
     */
    protected String contents;

    /**
     * Whether or not it is currently legal to move into this block
     */
    protected boolean isLegalMove;


    public abstract void registerView(View view);

    public abstract void notifyViews();

    public abstract void removeView(View view);

    public void setIsLegalMove(boolean isLegalMove) {
        this.isLegalMove = isLegalMove;
    }

    public boolean getIsLegalMove() {
        return this.isLegalMove;
    }

    /**
     * Returns the non-null String value of the contents of this block.
     *
     * @return The non-null String value
     */
    public String getContents() {
        return this.contents;
    }


    /**
     * Resets this block before starting a new game.
     */
    public void reset() {
        this.contents = "";
        this.isLegalMove = false;
    }

    /**
     * Sets the contents of this block to the given value.
     *
     * @param value The new value for the contents of this block
     * @throws IllegalArgumentException When the given value is null
     */
    public void setContents(String value) {
        if (contents == null) {
            throw new IllegalArgumentException("Contents must be non-null.");
        }
        this.contents = value;
    }

    public BoardController getBoardController(){
        return this.boardController;
    }
}
