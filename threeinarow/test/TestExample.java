import controller.ThreeInARowController;
import model.Player;
import model.RowBlock;
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

    @Before
    public void setUp() {
        view = new ThreeInARowBoardView(3,3);
        view.getGui().setVisible(false);
        controller = new ThreeInARowController(view,3, 3);

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
        RowBlock[][] blockdata = getCleanBlockData(3, 3);
        blockdata[0][0].setContents(Player.PLAYER_1.getMark());
        blockdata[1][0].setContents(Player.PLAYER_1.getMark());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setBlocksData(blockdata);
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
        RowBlock[][] blockdata = getCleanBlockData(3, 3);
        blockdata[0][0].setContents(Player.PLAYER_2.getMark());
        blockdata[1][0].setContents(Player.PLAYER_2.getMark());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setBlocksData(blockdata);
        controller.move(((BoardButtonView) view).getBlockButton(2, 0));

        assertNotEquals(((TextView) view).getTextView().getText(), controller.PLAYER_2_WIN_MESSAGE);
        controller.reset();
    }


    /**
     * Test controller's logic of checking a win by connecting a horizontal row
     */
    @Test
    public void testHorizontalRowWinCondition() {
        RowBlock[][] blockdata = getCleanBlockData(3, 3);
        blockdata[1][0].setContents(Player.PLAYER_1.getMark());
        blockdata[1][1].setContents(Player.PLAYER_1.getMark());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setBlocksData(blockdata);
        controller.move(((BoardButtonView) view).getBlockButton(1, 2));

        assertEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
        controller.reset();

    }

    /**
     * Test controller's logic of checking a win by connecting a horizontal row
     */
    @Test
    public void testHorizontalRowNoWinCondition() {
        RowBlock[][] blockdata = getCleanBlockData(3, 3);
        blockdata[1][0].setContents(Player.PLAYER_2.getMark());
        blockdata[1][1].setContents(Player.PLAYER_2.getMark());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setBlocksData(blockdata);
        controller.move(((BoardButtonView) view).getBlockButton(1, 2));

        assertNotEquals(((TextView) view).getTextView().getText(), controller.PLAYER_2_WIN_MESSAGE);
        controller.reset();

    }

    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     */
    @Test
    public void testAntiDiagonalWinCondition() {
        RowBlock[][] blockdata = getCleanBlockData(3, 3);
        blockdata[2][0].setContents(Player.PLAYER_1.getMark());
        blockdata[1][1].setContents(Player.PLAYER_1.getMark());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setBlocksData(blockdata);
        controller.move(((BoardButtonView) view).getBlockButton(0, 2));

        assertEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
        controller.reset();

    }


    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     */
    @Test
    public void testAntiDiagonalNoWinCondition() {
        RowBlock[][] blockdata = getCleanBlockData(3, 3);
        blockdata[2][0].setContents(Player.PLAYER_2.getMark());
        blockdata[1][1].setContents(Player.PLAYER_2.getMark());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setBlocksData(blockdata);
        controller.move(((BoardButtonView) view).getBlockButton(0, 2));

        assertNotEquals(((TextView) view).getTextView().getText(), controller.PLAYER_2_WIN_MESSAGE);
        controller.reset();

    }



    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     */
    @Test
    public void testMainDiagonalWinCondition() {
        RowBlock[][] blockdata = getCleanBlockData(3, 3);
        blockdata[0][0].setContents(Player.PLAYER_1.getMark());
        blockdata[1][1].setContents(Player.PLAYER_1.getMark());

        assertEquals(controller.getPlayer(), Player.PLAYER_1);

        controller.setBlocksData(blockdata);
        controller.move(((BoardButtonView) view).getBlockButton(2, 2));

        assertEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
        controller.reset();

    }

    /**
     * Test controller's logic of checking a win by connecting anti diagonal blocks
     * */
    @Test
    public void testMainDiagonalNoWinCondition() {
        RowBlock[][] blockdata = getCleanBlockData(3, 3);
        blockdata[0][0].setContents(Player.PLAYER_2.getMark());
        blockdata[1][1].setContents(Player.PLAYER_2.getMark());

        controller.setBlocksData(blockdata);
        controller.move(((BoardButtonView) view).getBlockButton(2, 2));

        assertNotEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
        controller.reset();

    }


    @Test(expected = IllegalArgumentException.class)
    public void testRowBlockContentPrecondition() {
        RowBlock block = new RowBlock(1, 1);
        block.setContents(null);
    }


    private RowBlock[][] getCleanBlockData(int row, int col){
        RowBlock[][] blockdata = new RowBlock[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                blockdata[r][c] = new RowBlock(r, c);
                blockdata[r][c].setContents("");
                blockdata[r][c].setIsLegalMove(true);
            }
        }


        return blockdata;
    }
}
