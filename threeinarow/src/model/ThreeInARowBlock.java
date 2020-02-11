package model;

import controller.BoardController;
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

    public ThreeInARowBlock(BoardController boardController, int row, int col) {

        if (boardController == null) {
            throw new IllegalArgumentException("The game must be non-null.");
        }

        this.boardController = boardController;
        this.observingViews = new ArrayList<>();
        this.row = row;
        this.col = col;
        this.reset();
    }


    @Override
    public void registerView(BoardView view) {
        this.observingViews.add(view);
    }

    @Override
    public void notifyViews() {

    }

    @Override
    public void removeView(BoardView view) {
        this.observingViews.remove(view);
    }
}
