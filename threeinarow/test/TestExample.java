import controller.InARowController;
import model.Player;
import model.RowBlock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.BoardButtonView;
import view.BoardView;
import view.InARowBoardButtonViewView;
import view.TextView;

import static org.junit.Assert.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    private InARowController controller;
    private BoardView view;

    @Before
    public void setUp() {
        view = new InARowBoardButtonViewView(3);
        view.getGui().setVisible(false);
        controller = new InARowController(view);

    }

    @After
    public void tearDown() {
        controller = null;
        view = null;
    }

    /**
     * Test controller's logic of checking a win by connecting a vertical row
     */
    @Test
    public void testVerticalRowWinCondition() {
        RowBlock[][] blockdata = getCleanBlockData(3);
        blockdata[0][0].setContents(Player.PLAYER_1.getMark());
        blockdata[1][0].setContents(Player.PLAYER_1.getMark());
        controller.setBlocksData(blockdata);
        controller.move(((BoardButtonView) view).getBlockButton(2, 0));

        assertEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
    }


    /**
     * Test controller's logic of checking a win by connecting a horizontal row
     */
    @Test
    public void testHorizontalRowWinCondition() {
        RowBlock[][] blockdata = getCleanBlockData(3);
        blockdata[1][0].setContents(Player.PLAYER_1.getMark());
        blockdata[1][1].setContents(Player.PLAYER_1.getMark());
        controller.setBlocksData(blockdata);
        controller.move(((BoardButtonView) view).getBlockButton(1, 2));

        assertEquals(((TextView) view).getTextView().getText(), controller.PLAYER_1_WIN_MESSAGE);
    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testNewBlockViolatesPrecondition() {
//        RowBlock block = new RowBlock(null);
//    }


    private RowBlock[][] getCleanBlockData(int size){
        RowBlock[][] blockdata = new RowBlock[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                blockdata[row][col] = new RowBlock(row, col);
                blockdata[row][col].setContents("");
                blockdata[row][col].setIsLegalMove(row == size - 1);
            }
        }


        return blockdata;
    }
}
