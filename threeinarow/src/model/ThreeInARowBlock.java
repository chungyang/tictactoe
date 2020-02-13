package model;

import view.BoardView;

import java.util.ArrayList;
import java.util.List;

/**
 * The model.ThreeInARowBlock class represents a given block in the game.
 */
public class ThreeInARowBlock extends RowBlock
{

    protected final List<BoardView> observingViews;

    private final int row;
    private final int col;

    public ThreeInARowBlock(int row, int col) {

        this.observingViews = new ArrayList<>();
        this.row = row;
        this.col = col;
        this.reset();
    }


}
