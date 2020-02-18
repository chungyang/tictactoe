package controller;

import model.BlockButton;
import model.Player;
import model.RowBlock;
import view.*;

import java.awt.event.*;



public class InARowController {

	private static final String GAME_END_NOWINNER = "Game ends in a draw";
	private static final String PLAYER_1_WIN_MESSAGE = "Player 1 wins!";
	private static final String PLAYER_2_WIN_MESSAGE = "Player 2 wins!";

	private BoardView boardView;
	private RowBlock[][] blocksData = new RowBlock[3][3];

	private int boardSize = 3;

	/**
	 * The current player taking their turn
	 */
	private Player player = Player.PLAYER_1;
	private int movesLeft = 9;

	/**
	 * Starts a new game in the GUI.
	 */
	public static void main(String[] args) {
		BoardView gameView = new InARowBoardButtonViewView(3);
		InARowController gameController = new InARowController(gameView);
	}

	public InARowController(BoardView boardView){

		this.boardView = boardView;
		addResetButtonListener((ResetButtonView) boardView);
		for(int row = 0; row < boardSize; row++){
			for(int col = 0; col < boardSize; col++){
				blocksData[row][col] = new RowBlock(row, col);
				addBoardButtonListener((BoardButtonView) boardView, row, col);
				blocksData[row][col].setContents("");
				blocksData[row][col].setIsLegalMove(row == 2);
				updateBlock((BoardButtonView) boardView, row, col);
			}
		}
	}


	public void move(BlockButton blockButton){

		--movesLeft;
		final int row = blockButton.getRow();
		final int col = blockButton.getColumn();
		blocksData[row][col].setContents(player.getMark());
		blocksData[row][col].setIsLegalMove(false);
		updateBlock((BoardButtonView) boardView, row, col);

		if(row > 0){
			blocksData[row - 1][col].setIsLegalMove(true);
			updateBlock((BoardButtonView) boardView, row - 1, col);
		}
		TextView textView = (TextView) boardView;

		if(checkAntiDiagonal(blockButton) || checkMainDiagonal(blockButton) || checkHorizontalRow(blockButton)
				|| checkVerticalRow(blockButton)){

			textView.setTextView(player == Player.PLAYER_1? PLAYER_1_WIN_MESSAGE : PLAYER_2_WIN_MESSAGE);
			endGame((BoardButtonView) boardView);
			return;
		}

		if(movesLeft == 0){
			textView.setTextView(GAME_END_NOWINNER);
			return;
		}

		switchPlayer();
		textView.setTextView(player.getMark() + ": Player " + player.getId());
	}


	/**
	 * Updates the block at the given row and column
	 * after one of the player's moves.
	 *
	 * @param row The row that contains the block
	 * @param column The column that contains the block
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
		for(int row = 0; row < boardSize; row++) {
			for(int col = 0; col < boardSize; col++) {
				boardButtonView.setEnabled(false, row, col);
			}
		}
	}


	private boolean checkHorizontalRow(BlockButton blockButton){

		final int row = blockButton.getRow();
		final String currentPlayerMark = player.getMark();

		for(int col = 0; col < boardSize; col++){
			if(!blocksData[row][col].getContents().equals(currentPlayerMark)){
				return false;
			}
		}

		return true;
	}

	private boolean checkVerticalRow(BlockButton blockButton){

		final int col = blockButton.getColumn();
		final String currentPlayerMark = player.getMark();

		for(int row = 0; row < boardSize; row++){
			if(!blocksData[row][col].getContents().equals(currentPlayerMark)){
				return false;
			}
		}

		return true;
	}

	private void switchPlayer(){

		if(player == Player.PLAYER_1){
			player = Player.PLAYER_2;
		}
		else{
			player = Player.PLAYER_1;
		}
	}

	/**
	 * Main diagonal goes from top left to bottom right
	 */
	private boolean checkMainDiagonal(BlockButton blockButton){

		final int row = blockButton.getRow();
		final int col = blockButton.getColumn();
		final int diff = Math.abs(col - row);
		final String currentPlayerMark = player.getMark();

		if(diff != 0){
			return false;
		}

		for(int i = 0; i < boardSize; i++){
			if(!blocksData[i][i].getContents().equals(currentPlayerMark)){
				return false;
			}
		}

		return true;
	}

	private void addBoardButtonListener(BoardButtonView boardButtonView, int row, int col){
		boardButtonView.addBlockButtonListener(new BlockController(), row, col);
	}

	private void addResetButtonListener(ResetButtonView resetButtonView){
		resetButtonView.addResetButtonListener(new ResetController());
	}


	/**
	 * Anti-diagonal goes from bottom left to top right
	 */
	private boolean checkAntiDiagonal(BlockButton blockButton){

		final int row = blockButton.getRow();
		final int col = blockButton.getColumn();
		final int diff = Math.abs(col - row);
		final String currentPlayerMark = player.getMark();

		if(diff != boardSize - 1){
			return false;
		}

		for(int i = boardSize - 1; i >= 0; i--){
			if(!blocksData[i][Math.abs(i - (boardSize - 1))].getContents().equals(currentPlayerMark)){
				return false;
			}
		}

		return true;
	}

	final class ResetController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			/**
			 * Resets the game to be able to start playing again.
			 */

			for(int row = 0; row < boardSize; row++) {
				for(int column = 0;column < boardSize;column++) {
					blocksData[row][column].reset();
					// Enable the bottom row
					blocksData[row][column].setIsLegalMove(row == boardSize - 1);
					updateBlock((BoardButtonView) boardView, row,column);
				}
			}
			player = Player.PLAYER_1;
			movesLeft = (int) Math.pow(boardSize, 2);
			TextView textView = (TextView) boardView;
			textView.setTextView("Player 1 to play " + Player.PLAYER_1.getMark());
		}
	}

	final class BlockController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			move((BlockButton) e.getSource());
		}
	}

}
