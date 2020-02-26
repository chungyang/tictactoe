import controller.ThreeInARowController;
import model.AbstractGameBoard;
import model.Player;
import model.RowBlock;
import model.ThreeInARowGameBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.BoardButtonView;
import view.BoardView;
import view.ThreeInARowBoardView;
import view.TextView;

import static org.junit.Assert.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    private ThreeInARowController controller;
    private BoardView view;
    private int row = 3;
    private int col = 3;

    @Before
    public void setUp() {
        view = new ThreeInARowBoardView(row,col);
        view.getGui().setVisible(false);
        controller = new ThreeInARowController(view,row, col);

    }

    @After
    public void tearDown() {
        controller = null;
        view = null;
    }

    @Test
    public void testNewGame() {
        assertEquals (Player.PLAYER_1, controller.getPlayer());
        assertEquals (9, controller.getMovesLeft());
    }



    /**
     * Test controller's logic of checking a win by connecting a vertical row
     */
    @Test
    public void testVerticalRowWinCondition() {
        AbstractGameBoard gameBoard = new ThreeInARowGameBoard(row, col);
        gameBoard.placeMarker(0, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 0, Player.PLAYER_1.getMarker());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setGameBoard(gameBoard);
        controller.move(((BoardButtonView) view).getBlockButton(2, 0));

        assertEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
        controller.reset();
    }

    /**
     * Test controller's logic of checking a no win situation when a vertical row
     * doesn't have the same marker.
     */
    @Test
    public void testVerticalRowNoWinCondition() {
        AbstractGameBoard gameBoard = new ThreeInARowGameBoard(row, col);
        gameBoard.placeMarker(0, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 0, Player.PLAYER_1.getMarker());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setGameBoard(gameBoard);
        controller.move(((BoardButtonView) view).getBlockButton(2, 0));

        assertNotEquals(((TextView) view).getTextView().getText(), controller.PLAYER_2_WIN_MESSAGE);
        controller.reset();
    }


    /**
     * Test controller's logic of checking a win by connecting a horizontal row
     */
    @Test
    public void testHorizontalRowWinCondition() {
        AbstractGameBoard gameBoard = new ThreeInARowGameBoard(row, col);
        gameBoard.placeMarker(1, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_1.getMarker());


        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setGameBoard(gameBoard);
        controller.move(((BoardButtonView) view).getBlockButton(1, 2));

        assertEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
        controller.reset();

    }

    /**
     * Test controller's logic of checking a win by connecting a horizontal row
     */
    @Test
    public void testHorizontalRowNoWinCondition() {
        AbstractGameBoard gameBoard = new ThreeInARowGameBoard(row, col);
        gameBoard.placeMarker(1, 0, Player.PLAYER_2.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_2.getMarker());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setGameBoard(gameBoard);
        controller.move(((BoardButtonView) view).getBlockButton(1, 2));

        assertNotEquals(((TextView) view).getTextView().getText(), controller.PLAYER_2_WIN_MESSAGE);
        controller.reset();

    }

    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     */
    @Test
    public void testAntiDiagonalWinCondition() {
        AbstractGameBoard gameBoard = new ThreeInARowGameBoard(row, col);
        gameBoard.placeMarker(2, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_1.getMarker());


        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setGameBoard(gameBoard);
        controller.move(((BoardButtonView) view).getBlockButton(0, 2));

        assertEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
        controller.reset();

    }


    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     */
    @Test
    public void testAntiDiagonalNoWinCondition() {
        AbstractGameBoard gameBoard = new ThreeInARowGameBoard(row, col);
        gameBoard.placeMarker(2, 0, Player.PLAYER_2.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_2.getMarker());


        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setGameBoard(gameBoard);
        controller.move(((BoardButtonView) view).getBlockButton(0, 2));

        assertNotEquals(((TextView) view).getTextView().getText(), controller.PLAYER_2_WIN_MESSAGE);
        controller.reset();

    }



    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     */
    @Test
    public void testMainDiagonalWinCondition() {
        AbstractGameBoard gameBoard = new ThreeInARowGameBoard(row, col);
        gameBoard.placeMarker(0, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_1.getMarker());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setGameBoard(gameBoard);
        controller.move(((BoardButtonView) view).getBlockButton(2, 2));

        assertEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
        controller.reset();

    }

    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     * */
    @Test
    public void testMainDiagonalNoWinCondition() {
        AbstractGameBoard gameBoard = new ThreeInARowGameBoard(row, col);
        gameBoard.placeMarker(0, 0, Player.PLAYER_2.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_2.getMarker());

        controller.setGameBoard(gameBoard);
        controller.move(((BoardButtonView) view).getBlockButton(2, 2));

        assertNotEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
        controller.reset();

    }


    @Test(expected = IllegalArgumentException.class)
    public void testRowBlockContentPrecondition() {
        RowBlock block = new RowBlock(1, 1);
        block.setContents(null);
    }

}
