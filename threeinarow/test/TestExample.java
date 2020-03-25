import controller.ThreeInARowController;
import model.AbstractGameBoard;
import model.Player;
import model.RowBlock;
import model.ThreeInARowGameBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.*;

import static org.junit.Assert.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    private ThreeInARowController controller;
    private AbstractGameBoard gameBoard;
    private BoardView view;
    private int row = 3;
    private int col = 3;

    @Before
    public void setUp() {
        view = new ThreeInARowBoardView(row,col);
        view.setVisible(true);
        controller = new ThreeInARowController(view,row, col);
        gameBoard = new ThreeInARowGameBoard(row, col);
        controller.setGameBoard(gameBoard);

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


    @Test
    public void testSwitchPlayer(){
        controller.reset();
        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.move((BlockButton)((BoardButtonView) view).getBlockButton(2, 0));

        assertEquals(controller.getPlayer(), Player.PLAYER_2);
    }

    /**
     * Test controller's logic of checking a win by connecting a vertical row
     */
    @Test
    public void testVerticalRowWinCondition() {
        gameBoard.reset();
        gameBoard.placeMarker(0, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 0, Player.PLAYER_1.getMarker());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.move((BlockButton)((BoardButtonView) view).getBlockButton(2, 0));

        assertEquals(((TextView) view).getText(), controller.PLAYER_1_WIN_MESSAGE);
    }

    /**
     * Test controller's logic of checking a no win situation when a vertical row
     * doesn't have the same marker.
     */
    @Test
    public void testVerticalRowNoWinCondition() {
        gameBoard.reset();
        gameBoard.placeMarker(0, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 0, Player.PLAYER_1.getMarker());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setGameBoard(gameBoard);
        controller.move((BlockButton)((BoardButtonView) view).getBlockButton(2, 0));

        assertNotEquals(((TextView) view).getText(), controller.PLAYER_2_WIN_MESSAGE);
    }


    /**
     * Test controller's logic of checking a win by connecting a horizontal row
     */
    @Test
    public void testHorizontalRowWinCondition() {
        gameBoard.reset();
        gameBoard.placeMarker(1, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_1.getMarker());


        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setGameBoard(gameBoard);
        controller.move((BlockButton)((BoardButtonView) view).getBlockButton(1, 2));

        assertEquals(((TextView) view).getText(), controller.PLAYER_1_WIN_MESSAGE);

    }

    /**
     * Test controller's logic of checking a win by connecting a horizontal row
     */
    @Test
    public void testHorizontalRowNoWinCondition() {
        gameBoard.reset();
        gameBoard.placeMarker(1, 0, Player.PLAYER_2.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_2.getMarker());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.move((BlockButton)((BoardButtonView) view).getBlockButton(1, 2));

        assertNotEquals(((TextView) view).getText(), controller.PLAYER_2_WIN_MESSAGE);
    }

    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     */
    @Test
    public void testAntiDiagonalWinCondition() {
        gameBoard.reset();
        gameBoard.placeMarker(2, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_1.getMarker());


        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.move((BlockButton)((BoardButtonView) view).getBlockButton(0, 2));

        assertEquals(((TextView) view).getText(), controller.PLAYER_1_WIN_MESSAGE);
    }


    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     */
    @Test
    public void testAntiDiagonalNoWinCondition() {
        gameBoard.reset();
        gameBoard.placeMarker(2, 0, Player.PLAYER_2.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_2.getMarker());


        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.move((BlockButton)((BoardButtonView) view).getBlockButton(0, 2));

        assertNotEquals(((TextView) view).getText(), controller.PLAYER_2_WIN_MESSAGE);
    }



    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     */
    @Test
    public void testMainDiagonalWinCondition() {
        gameBoard.reset();
        gameBoard.placeMarker(0, 0, Player.PLAYER_1.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_1.getMarker());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.move((BlockButton)((BoardButtonView) view).getBlockButton(2, 2));

        assertEquals(((TextView) view).getText(), controller.PLAYER_1_WIN_MESSAGE);
    }

    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     * */
    @Test
    public void testMainDiagonalNoWinCondition() {
        gameBoard.reset();
        gameBoard.placeMarker(0, 0, Player.PLAYER_2.getMarker());
        gameBoard.placeMarker(1, 1, Player.PLAYER_2.getMarker());

        controller.move((BlockButton)((BoardButtonView) view).getBlockButton(2, 2));

        assertNotEquals(((TextView) view).getText(), controller.PLAYER_1_WIN_MESSAGE);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRowBlockContentPrecondition() {
        RowBlock block = new RowBlock(1, 1);
        block.setContents(null);
    }

}
