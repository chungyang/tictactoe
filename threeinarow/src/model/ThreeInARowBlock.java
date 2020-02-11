package model;

import controller.BoardController;
import model.RowBlock;

/**
 * The model.ThreeInARowBlock class represents a given block in the game.
 */
public class ThreeInARowBlock extends RowBlock
{

    public ThreeInARowBlock(BoardController boardController) {

	if (boardController == null) {
	    throw new IllegalArgumentException("The game must be non-null.");
	}
	
	this.boardController = boardController;
	this.reset();
    }


}
