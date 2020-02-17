package controller;

import model.BlockButton;
import model.Player;
import model.RowBlock;
import view.BoardButton;
import view.BoardView;
import view.InARowBoardButton;
import view.TextView;

import javax.swing.JButton;
import java.awt.event.*;



public class InARowController {

	private static final String GAME_END_NOWINNER = "Game ends in a draw";
	private static final String PLAYER_1_WIN_MESSAGE = "model.Player 1 wins!";
	private static final String PLAYER_2_WIN_MESSAGE = "model.Player 2 wins!";

	private BoardView boardView;

//	private JFrame gui = new JFrame("Three in a Row");
	private RowBlock[][] blocksData = new RowBlock[3][3];
//	private JButton[][] blocks;
//	private JButton reset = new JButton("Reset");
//	private JTextArea playerturn;
	/**
	 * The current player taking their turn
	 */
	private Player player = Player.PLAYER_1;
	private int movesLeft = 9;

	/**
	 * Starts a new game in the GUI.
	 */
	public static void main(String[] args) {
		BoardView gameView = new InARowBoardButton(3, 3);
		InARowController gameController = new InARowController(gameView, 3, 3);
//		gameController.playerturn = gameView.playerturn;
//		gameController.blocks = gameView.blocks;
//		gameView.gui.setVisible(true);
	}

	public InARowController(BoardView boardView, int rowNumber, int colNumber){
		this.boardView = boardView;

		for(int row = 0; row < rowNumber; row++){
			for(int col = 0; col < colNumber; col++){
				blocksData[row][col] = new RowBlock(row, col);
				addBoardButtonListener((BoardButton) boardView, row, col);
				blocksData[row][col].setContents("");
				blocksData[row][col].setIsLegalMove(row == 2);
				updateBlock((BoardButton) boardView, row, col);
			}
		}
	}

	private void addBoardButtonListener(BoardButton boardButton, int row, int col){
		boardButton.addBlockButtonListener(new BlockController(), row, col);
	}

	public void move(BlockButton blockButton){

		--movesLeft;
		TextView textView = (TextView) boardView;

		if(movesLeft % 2 == 1) {

			textView.setTextView(Player.PLAYER_1.getMark() + ": Player 1");
		} else{
			textView.setTextView(Player.PLAYER_2.getMark() + ": Player 2");
		}
	}

	/**
	 * Moves the current player into the given block.
	 *
	 * @param block The block to be moved to by the current player
	 */
//	public void move(JButton block) {
//
//		--movesLeft;
//
//		if(movesLeft % 2 == 1) {
//			playerturn.setText("'X': model.Player 1");
//		} else{
//			playerturn.setText("'O': model.Player 2");
//		}
//
//		if(player.equals("1")) {
//			// Check whether player 1 won
//			if(block==blocks[0][0]) {
//				blocksData[0][0].setContents("X");
//				blocksData[0][0].setIsLegalMove(false);
//				updateBlock(0,0);
//				player = "2";
//				if(movesLeft<7) {
//					if((blocksData[0][0].getContents().equals(blocksData[0][1].getContents()) &&
//							blocksData[0][1].getContents().equals(blocksData[0][2].getContents())) ||
//							(blocksData[0][0].getContents().equals(blocksData[1][0].getContents()) &&
//									blocksData[1][0].getContents().equals(blocksData[2][0].getContents())) ||
//							(blocksData[0][0].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[2][2].getContents()))) {
//						playerturn.setText("model.Player 1 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[0][1]) {
//				blocksData[0][1].setContents("X");
//				blocksData[0][1].setIsLegalMove(false);
//				updateBlock(0,1);
//				player = "2";
//				if(movesLeft<7) {
//					if((blocksData[0][1].getContents().equals(blocksData[0][0].getContents()) &&
//							blocksData[0][0].getContents().equals(blocksData[0][2].getContents())) ||
//							(blocksData[0][1].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[2][1].getContents()))) {
//						playerturn.setText("model.Player 1 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[0][2]) {
//				blocksData[0][2].setContents("X");
//				blocksData[0][2].setIsLegalMove(false);
//				updateBlock(0,2);
//				player = "2";
//				if(movesLeft<7) {
//					if((blocksData[0][2].getContents().equals(blocksData[0][1].getContents()) &&
//							blocksData[0][1].getContents().equals(blocksData[0][0].getContents())) ||
//							(blocksData[0][2].getContents().equals(blocksData[1][2].getContents()) &&
//									blocksData[1][2].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[0][2].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[2][0].getContents()))) {
//						playerturn.setText("model.Player 1 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[1][0]) {
//				blocksData[1][0].setContents("X");
//				blocksData[1][0].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[0][0].setIsLegalMove(true);
//				updateBlock(1,0);
//				updateBlock(0,0);
//				player = "2";
//				if(movesLeft<7) {
//					if((blocksData[1][0].getContents().equals(blocksData[1][1].getContents()) &&
//							blocksData[1][1].getContents().equals(blocksData[1][2].getContents())) ||
//							(blocksData[1][0].getContents().equals(blocksData[0][0].getContents()) &&
//									blocksData[0][0].getContents().equals(blocksData[2][0].getContents()))) {
//						playerturn.setText("model.Player 1 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[1][1]) {
//				blocksData[1][1].setContents("X");
//				blocksData[1][1].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[0][1].setIsLegalMove(true);
//				updateBlock(1,1);
//				updateBlock(0,1);
//				player = "2";
//				if(movesLeft<7) {
//					if((blocksData[1][1].getContents().equals(blocksData[1][0].getContents()) &&
//							blocksData[1][0].getContents().equals(blocksData[1][2].getContents())) ||
//							(blocksData[1][1].getContents().equals(blocksData[0][1].getContents()) &&
//									blocksData[0][1].getContents().equals(blocksData[2][1].getContents())) ||
//							(blocksData[1][1].getContents().equals(blocksData[0][0].getContents()) &&
//									blocksData[0][0].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[1][1].getContents().equals(blocksData[0][2].getContents()) &&
//									blocksData[0][2].getContents().equals(blocksData[2][0].getContents()))) {
//						playerturn.setText("model.Player 1 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[1][2]) {
//				blocksData[1][2].setContents("X");
//				blocksData[1][2].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[0][2].setIsLegalMove(true);
//				updateBlock(1,2);
//				updateBlock(0,2);
//				player = "2";
//				if(movesLeft<7) {
//					if((blocksData[1][2].getContents().equals(blocksData[0][2].getContents()) &&
//							blocksData[0][2].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[1][2].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[1][0].getContents()))) {
//						playerturn.setText("model.Player 1 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[2][0]) {
//				blocksData[2][0].setContents("X");
//				blocksData[2][0].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[1][0].setIsLegalMove(true);
//				updateBlock(2,0);
//				updateBlock(1,0);
//				player = "2";
//				if(movesLeft<7) {
//					if((blocksData[2][0].getContents().equals(blocksData[2][1].getContents()) &&
//							blocksData[2][1].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[2][0].getContents().equals(blocksData[1][0].getContents()) &&
//									blocksData[1][0].getContents().equals(blocksData[0][0].getContents())) ||
//							(blocksData[2][0].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[0][2].getContents()))) {
//						playerturn.setText("model.Player 1 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[2][1]) {
//				blocksData[2][1].setContents("X");
//				blocksData[2][1].setIsLegalMove(false);
//				// Enabled the space on top of this one
//				blocksData[1][1].setIsLegalMove(true);
//				updateBlock(2,1);
//				updateBlock(1,1);
//				player = "2";
//				if(movesLeft<7) {
//					if((blocksData[2][1].getContents().equals(blocksData[2][0].getContents()) &&
//							blocksData[2][0].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[2][1].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[0][1].getContents()))) {
//						playerturn.setText("model.Player 1 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[2][2]) {
//				blocksData[2][2].setContents("X");
//				blocksData[2][2].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[1][2].setIsLegalMove(true);
//				updateBlock(2,2);
//				updateBlock(1,2);
//				player = "2";
//				if(movesLeft<7) {
//					if((blocksData[2][2].getContents().equals(blocksData[2][1].getContents()) &&
//							blocksData[2][1].getContents().equals(blocksData[2][0].getContents())) ||
//							(blocksData[2][2].getContents().equals(blocksData[1][2].getContents()) &&
//									blocksData[1][2].getContents().equals(blocksData[0][2].getContents())) ||
//							(blocksData[2][2].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[0][0].getContents()))) {
//						playerturn.setText("model.Player 1 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			}
//		} else {
//			// Check whether player 2 won
//			if(block==blocks[0][0]) {
//				blocksData[0][0].setContents("O");
//				blocksData[0][0].setIsLegalMove(false);
//				updateBlock(0,0);
//				player = "1";
//				if(movesLeft<7) {
//					if((blocksData[0][0].getContents().equals(blocksData[0][1].getContents()) &&
//							blocksData[0][1].getContents().equals(blocksData[0][2].getContents())) ||
//							(blocksData[0][0].getContents().equals(blocksData[1][0].getContents()) &&
//									blocksData[1][0].getContents().equals(blocksData[2][0].getContents())) ||
//							(blocksData[0][0].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[2][2].getContents()))) {
//						playerturn.setText("model.Player 2 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[0][1]) {
//				blocksData[0][1].setContents("O");
//				blocksData[0][1].setIsLegalMove(false);
//				updateBlock(0,1);
//				player = "1";
//				if(movesLeft<7) {
//					if((blocksData[0][1].getContents().equals(blocksData[0][0].getContents()) &&
//							blocksData[0][0].getContents().equals(blocksData[0][2].getContents())) ||
//							(blocksData[0][1].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[2][1].getContents()))) {
//						playerturn.setText("model.Player 2 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[0][2]) {
//				blocksData[0][2].setContents("O");
//				blocksData[0][2].setIsLegalMove(false);
//				updateBlock(0,2);
//				player = "1";
//				if(movesLeft<7) {
//					if((blocksData[0][2].getContents().equals(blocksData[0][1].getContents()) &&
//							blocksData[0][1].getContents().equals(blocksData[0][0].getContents())) ||
//							(blocksData[0][2].getContents().equals(blocksData[1][2].getContents()) &&
//									blocksData[1][2].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[0][2].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[2][0].getContents()))) {
//						playerturn.setText("model.Player 2 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[1][0]) {
//				blocksData[1][0].setContents("O");
//				blocksData[1][0].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[0][0].setIsLegalMove(true);
//				updateBlock(1,0);
//				updateBlock(0,0);
//				player = "1";
//				if(movesLeft<7) {
//					if((blocksData[1][0].getContents().equals(blocksData[1][1].getContents()) &&
//							blocksData[1][1].getContents().equals(blocksData[1][2].getContents())) ||
//							(blocksData[1][0].getContents().equals(blocksData[0][0].getContents()) &&
//									blocksData[0][0].getContents().equals(blocksData[2][0].getContents()))) {
//						playerturn.setText("model.Player 2 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[1][1]) {
//				blocksData[1][1].setContents("O");
//				blocksData[1][1].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[0][1].setIsLegalMove(true);
//				updateBlock(1,1);
//				updateBlock(0,1);
//				player = "1";
//				if(movesLeft<7) {
//					if((blocksData[1][1].getContents().equals(blocksData[1][0].getContents()) &&
//							blocksData[1][0].getContents().equals(blocksData[1][2].getContents())) ||
//							(blocksData[1][1].getContents().equals(blocksData[0][1].getContents()) &&
//									blocksData[0][1].getContents().equals(blocksData[2][1].getContents())) ||
//							(blocksData[1][1].getContents().equals(blocksData[0][0].getContents()) &&
//									blocksData[0][0].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[1][1].getContents().equals(blocksData[0][2].getContents()) &&
//									blocksData[0][2].getContents().equals(blocksData[2][0].getContents()))) {
//						playerturn.setText("model.Player 2 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[1][2]) {
//				blocksData[1][2].setContents("O");
//				blocksData[1][2].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[0][2].setIsLegalMove(true);
//				updateBlock(1,2);
//				updateBlock(0,2);
//				player = "1";
//				if(movesLeft<7) {
//					if((blocksData[1][2].getContents().equals(blocksData[0][2].getContents()) &&
//							blocksData[0][2].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[1][2].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[1][0].getContents()))) {
//						playerturn.setText("model.Player 2 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[2][0]) {
//				blocksData[2][0].setContents("O");
//				blocksData[2][0].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[1][0].setIsLegalMove(true);
//				updateBlock(2,0);
//				updateBlock(1,0);
//				player = "1";
//				if(movesLeft<7) {
//					if((blocksData[2][0].getContents().equals(blocksData[2][1].getContents()) &&
//							blocksData[2][1].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[2][0].getContents().equals(blocksData[1][0].getContents()) &&
//									blocksData[1][0].getContents().equals(blocksData[0][0].getContents())) ||
//							(blocksData[2][0].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[0][2].getContents()))) {
//						playerturn.setText("model.Player 2 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[2][1]) {
//				blocksData[2][1].setContents("O");
//				blocksData[2][1].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[1][1].setIsLegalMove(true);
//				updateBlock(2,1);
//				updateBlock(1,1);
//				player = "1";
//				if(movesLeft<7) {
//					if((blocksData[2][1].getContents().equals(blocksData[2][0].getContents()) &&
//							blocksData[2][0].getContents().equals(blocksData[2][2].getContents())) ||
//							(blocksData[2][1].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[0][1].getContents()))) {
//						playerturn.setText("model.Player 2 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			} else if(block==blocks[2][2]) {
//				blocksData[2][2].setContents("O");
//				blocksData[2][2].setIsLegalMove(false);
//				// Enable the space on top of this one
//				blocksData[1][2].setIsLegalMove(true);
//				updateBlock(2,2);
//				updateBlock(1,2);
//				player = "1";
//				if(movesLeft<7) {
//					if((blocksData[2][2].getContents().equals(blocksData[2][1].getContents()) &&
//							blocksData[2][1].getContents().equals(blocksData[2][0].getContents())) ||
//							(blocksData[2][2].getContents().equals(blocksData[1][2].getContents()) &&
//									blocksData[1][2].getContents().equals(blocksData[0][2].getContents())) ||
//							(blocksData[2][2].getContents().equals(blocksData[1][1].getContents()) &&
//									blocksData[1][1].getContents().equals(blocksData[0][0].getContents()))) {
//						playerturn.setText("model.Player 2 wins!");
//						endGame();
//					} else if(movesLeft==0) {
//						playerturn.setText(GAME_END_NOWINNER);
//					}
//				}
//			}
//		}
//	}

	/**
	 * Updates the block at the given row and column
	 * after one of the player's moves.
	 *
	 * @param row The row that contains the block
	 * @param column The column that contains the block
	 */
	protected void updateBlock(BoardButton boardButton, int row, int column) {
		BlockButton blockButton = boardButton.getBlockButton(row, column);
		blockButton.setText(blocksData[row][column].getContents());
		blockButton.setEnabled(blocksData[row][column].getIsLegalMove());
	}

	/**
	 * Ends the game disallowing further player turns.
	 */

//	public void endGame() {
//		for(int row = 0;row<3;row++) {
//			for(int column = 0;column<3;column++) {
//				blocks[row][column].setEnabled(false);
//			}
//		}
//	}



	final class ResetController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			/**
			 * Resets the game to be able to start playing again.
			 */

			for(int row = 0; row<3; row++) {
				for(int column = 0;column<3;column++) {
					blocksData[row][column].reset();
					// Enable the bottom row
					blocksData[row][column].setIsLegalMove(row == 2);
					updateBlock((BoardButton) boardView, row,column);
				}
			}
			player = Player.PLAYER_2;
			movesLeft = 9;
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
