package model;

/**
 * The model.RowBlock class represents a given block in the game.
 */
public class RowBlock {

    private final int row;
    private final int col;

    public RowBlock(int row, int col) {

        this.row = row;
        this.col = col;
        this.reset();
    }


    /**
     * The current value of the contents of this block
     */
    protected String contents;

    protected boolean isLegalMove;


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


    public void setIsLegalMove(boolean isLegal){
        this.isLegalMove = isLegal;
    }

    public boolean getIsLegalMove(){
        return this.isLegalMove;
    }


}
