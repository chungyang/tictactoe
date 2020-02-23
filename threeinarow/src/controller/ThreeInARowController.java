package controller;

import model.BlockButton;
import model.Player;
import model.RowBlock;
import view.*;

import java.awt.event.*;



public class ThreeInARowController {

	public static final String GAME_END_NOWINNER = "Game ends in a draw";
	public static final String PLAYER_1_WIN_MESSAGE = "Player 1 wins!";
	public static final String PLAYER_2_WIN_MESSAGE = "Player 2 wins!";
	public static final String PLAYER_1_TURN_MESSAGE = Player.PLAYER_1.getMark() + ": Player " + Player.PLAYER_1.getId();
	public static final String PLAYER_2_TURN_MESSAGE = Player.PLAYER_2.getMark() + ": Player " + Player.PLAYER_2.getId();

	private BoardView boardView;
	private RowBlock[][] blocksData;

	private int rowSize;
	private int columnSize;

	/**
	 * The current player taking their turn
	 */
	private Player player = Player.PLAYER_1;
	private int movesLeft = 9;

	/**
	 * Starts a new game in the GUI.
	 */
	public static void main(String[] args) {
		BoardView gameView = new ThreeInARowBoardView(4, 3);
		ThreeInARowController gameController = new ThreeInARowController(gameView, 4, 3);
	}

	public ThreeInARowController(BoardView boardView, int rowSize, int columnSize) {

		this.boardView = boardView;
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		addResetButtonListener((ResetButtonView) boardView);

		blocksData = new RowBlock[rowSize][columnSize];

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < columnSize; col++) {
				blocksData[row][col] = new RowBlock(row, col);
				addBoardButtonListener((BoardButtonView) boardView, row, col);
				blocksData[row][col].setContents("");
				blocksData[row][col].setIsLegalMove(true);
				updateBlock((BoardButtonView) boardView, row, col);
			}
		}
	}


	public void move(BlockButton blockButton) {

		--movesLeft;
		final int row = blockButton.getRow();
		final int col = blockButton.getColumn();
		blocksData[row][col].setContents(player.getMark());
		blocksData[row][col].setIsLegalMove(false);
		updateBlock((BoardButtonView) boardView, row, col);

		TextView textView = (TextView) boardView;

		if (checkAntiDiagonal(blockButton) || checkMainDiagonal(blockButton) || checkHorizontalRow(blockButton)
				|| checkVerticalRow(blockButton)) {

			textView.setTextView(player == Player.PLAYER_1 ? PLAYER_1_WIN_MESSAGE : PLAYER_2_WIN_MESSAGE);
			endGame((BoardButtonView) boardView);
			return;
		}

		if (movesLeft == 0) {
			textView.setTextView(GAME_END_NOWINNER);
			return;
		}

		switchPlayer();
		textView.setTextView(player == Player.PLAYER_1 ? PLAYER_1_TURN_MESSAGE : PLAYER_2_TURN_MESSAGE);
	}

	public void reset() {

		/**
		 * Resets the game to be able to start playing again.
		 */

		for (int row = 0; row < rowSize; row++) {
			for (int column = 0; column < columnSize; column++) {
				blocksData[row][column].reset();
				// Enable the bottom row
				blocksData[row][column].setIsLegalMove(true);
				updateBlock((BoardButtonView) boardView, row, column);
			}
		}
		player = Player.PLAYER_1;
		movesLeft = rowSize * columnSize;
		TextView textView = (TextView) boardView;
		textView.setTextView("Player 1 to play " + Player.PLAYER_1.getMark());

	}

	public void setBlocksData(RowBlock[][] blocksData) {
		this.blocksData = blocksData;
	}

	public Player getPlayer(){return this.player;}

	public int getMovesLeft(){return this.movesLeft;}


	/**
	 * Updates the block at the given row and column
	 * after one of the player's moves.
	 */
	protected void updateBlock(BoardButtonView boardButtonView, int row, int column) {
		BlockButton blockButton = boardButtonView.getBlockButton(row, column);
		blockButton.setText(blocksData[row][column].getContents());
		blockButton.setEnabled(blocksData[row][column].getIsLegalMove());
	}

	/**
	 * Ends the game disallowing further player turns.
	 */

	private void endGame(BoardButtonView boardButtonView) {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < columnSize; col++) {
				boardButtonView.setEnabled(false, row, col);
			}
		}
	}


	private boolean checkHorizontalRow(BlockButton blockButton) {

		final int row = blockButton.getRow();
		final String currentPlayerMark = player.getMark();
		int count = 0;

		for (int col = 0; col < columnSize; col++) {
			if (blocksData[row][col].getContents().equals(currentPlayerMark)) {
				count++;
			} else {
				count = 0;
			}
		}

		return count == 3;
	}


	private boolean checkVerticalRow(BlockButton blockButton) {

		final int col = blockButton.getColumn();
		final String currentPlayerMark = player.getMark();
		int count = 0;

		for (int row = 0; row < rowSize; row++) {
			if (blocksData[row][col].getContents().equals(currentPlayerMark)) {
				count++;
			} else {
				count = 0;
			}
		}

		return count == 3;
	}

	private void switchPlayer() {

		if (player == Player.PLAYER_1) {
			player = Player.PLAYER_2;
		} else {
			player = Player.PLAYER_1;
		}
	}

	/**
	 * Main diagonal goes from top left to bottom right
	 */
	private boolean checkMainDiagonal(BlockButton blockButton) {

		final int row = blockButton.getRow();
		final int col = blockButton.getColumn();
		final String currentPlayerMark = player.getMark();

		int startingRow = Math.max(0, row - col);
		int startingCol = Math.max(0, col - row);

		int count = 0;

		while (startingRow < this.rowSize && startingCol < this.columnSize) {
			if (blocksData[startingRow][startingCol].getContents().equals(currentPlayerMark)) {
				count++;
			} else {
				count = 0;
			}

			startingRow++;
			startingCol++;
		}

		return count == 3;
	}


	/**
	 * Anti-diagonal goes from bottom left to top right
	 */
	private boolean checkAntiDiagonal(BlockButton blockButton) {

		final int row = blockButton.getRow();
		final int col = blockButton.getColumn();
		final String currentPlayerMark = player.getMark();
		final int sum = row + col;

		int startingRow = sum > (rowSize - 1) ? sum - (rowSize - 1) : sum;
		int startingCol = sum - startingRow;

		int count = 0;

		while (startingRow >= 0 && startingCol < this.columnSize) {

			if (blocksData[startingRow][startingCol].getContents().equals(currentPlayerMark)) {
				count++;
			} else {
				count = 0;
			}

			startingRow--;
			startingCol++;
		}

		return count == 3;
	}


	private void addBoardButtonListener(BoardButtonView boardButtonView, int row, int col) {
		boardButtonView.addBlockButtonListener(new BlockController(), row, col);
	}

	private void addResetButtonListener(ResetButtonView resetButtonView) {
		resetButtonView.addResetButtonListener(new ResetController());
	}

	final class ResetController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			reset();
		}
	}

	final class BlockController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			move((BlockButton) e.getSource());
		}
	}
}