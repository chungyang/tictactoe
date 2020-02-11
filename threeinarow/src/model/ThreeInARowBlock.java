package model;

import controller.BoardController;
import view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * The model.ThreeInARowBlock class represents a given block in the game.
 */
public class ThreeInARowBlock extends RowBlock
{

    protected final List<View> observingViews;

    public ThreeInARowBlock(BoardController boardController) {

        if (boardController == null) {
            throw new IllegalArgumentException("The game must be non-null.");
        }

        this.boardController = boardController;
        this.observingViews = new ArrayList<>();
        this.reset();
    }


    @Override
    public void registerView(View view) {
        this.observingViews.add(view);
    }

    @Override
    public void notifyViews() {

    }

    @Override
    public void removeView(View view) {
        this.observingViews.remove(view);
    }
}
