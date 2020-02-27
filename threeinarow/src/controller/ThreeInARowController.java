package controller;

import model.*;
import view.*;

import java.awt.event.*;



public class ThreeInARowController {

	public static final String GAME_END_NOWINNER = "Game ends in a draw";
	public static final String PLAYER_1_WIN_MESSAGE = "Player 1 wins!";
	public static final String PLAYER_2_WIN_MESSAGE = "Player 2 wins!";
	public static final String PLAYER_1_TURN_MESSAGE = Player.PLAYER_1.getMarker() + ": Player " + Player.PLAYER_1.getId();
	public static final String PLAYER_2_TURN_MESSAGE = Player.PLAYER_2.getMarker() + ": Player " + Player.PLAYER_2.getId();

	private BoardView boardView;
	private AbstractGameBoard gameBoard;

	private int rowSize;
	private int columnSize;

	/**
	 * The current player taking their turn
	 */
	private Player player = Player.PLAYER_1;

	/**
	 * Starts a new game in the GUI.
	 */
	public static void main(String[] args) {
		int row = args != null && args.length == 2? Integer.valueOf(args[0]) : 3;
		int col = args != null && args.length == 2? Integer.valueOf(args[1]) : 3;

		BoardView gameView = new ThreeInARowBoardView(row, col);
		ThreeInARowController gameController = new ThreeInARowController(gameView, row, col);
	}

	public ThreeInARowController(BoardView boardView, int rowSize, int columnSize) {

		checkRequiredViewType(boardView);
		this.boardView = boardView;
		this.gameBoard = new ThreeInARowGameBoard(rowSize, columnSize);
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		addResetButtonListener((ResetButtonView) boardView);

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < columnSize; col++) {
				addBoardButtonListener((BoardButtonView) boardView, row, col);
				updateBlock((BoardButtonView) boardView, row, col);
			}
		}
	}


	public void move(BlockButton blockButton) {

		final int row = blockButton.getRow();
		final int col = blockButton.getColumn();

		TextView textView = (TextView) boardView;
		boolean winninMove = gameBoard.placeMarker(row, col, player.getMarker());
		updateBlock((BoardButtonView) boardView, row, col);

		if(winninMove){
			textView.setTextView(player == Player.PLAYER_1 ? PLAYER_1_WIN_MESSAGE : PLAYER_2_WIN_MESSAGE);
			endGame((BoardButtonView) boardView);
			return;
		}

		if (gameBoard.getMovesLeft() == 0) {
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

		gameBoard.reset();
		for (int row = 0; row < rowSize; row++) {
			for (int column = 0; column < columnSize; column++) {
				updateBlock((BoardButtonView) boardView, row, column);
			}
		}
		player = Player.PLAYER_1;
		TextView textView = (TextView) boardView;
		textView.setTextView("Player 1 to play " + Player.PLAYER_1.getMarker());

	}

	public void setGameBoard(AbstractGameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public Player getPlayer(){return this.player;}

	public int getMovesLeft(){return this.gameBoard.getMovesLeft();}


	/**
	 * Updates the block at the given row and column
	 * after one of the player's moves.
	 */
	protected void updateBlock(BoardButtonView boardButtonView, int row, int column) {
		BlockButton blockButton = boardButtonView.getBlockButton(row, column);
		blockButton.setText(gameBoard.getBlockContent(row, column));
		blockButton.setEnabled(gameBoard.isLegalMove(row, column));
	}


	private void checkRequiredViewType(BoardView view){

		if(!(view instanceof TextView)){
			throw new IllegalArgumentException("View must be of TextView type");
		}

		if(!(view instanceof ResetButtonView)){
			throw new IllegalArgumentException("View must be of ResetButtonView type");
		}

		if(!(view instanceof BoardButtonView)){
			throw new IllegalArgumentException("View must be of BoardButtonView type");
		}
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

	private void switchPlayer() {

		if (player == Player.PLAYER_1) {
			player = Player.PLAYER_2;
		} else {
			player = Player.PLAYER_1;
		}
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