package controller;

import model.RowBlock;
import view.BoardView;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;



public class InARowController {

	private static final String GAME_END_NOWINNER = "Game ends in a draw";
	private static final String PLAYER_1_WIN_MESSAGE = "Player 1 wins!";
	private static final String PLAYER_2_WIN_MESSAGE = "Player 2 wins!";


	private enum Player{
		PLAYER_1("X"), PLAYER_2("O");

		private final String mark;

		Player(String mark){
			this.mark = mark;
		}

		public String getMark(){
			return this.mark;
		}
	}

	private BoardView boardView;

	private JFrame gui = new JFrame("Three in a Row");
	private RowBlock[][] blocksData = new RowBlock[3][3];
	private JButton[][] blocks = new JButton[3][3];
	private JButton reset = new JButton("Reset");
	private JTextArea playerturn = new JTextArea();
	/**
	 * The current player taking their turn
	 */
	private String player = "1";
	private int movesLeft = 9;

	/**
	 * Starts a new game in the GUI.
	 */
	public static void main(String[] args) {
		InARowController game = new InARowController();
		game.gui.setVisible(true);
	}

	public InARowController(BoardView boardView, int rowNumber, int colNumber){
		this.boardView = boardView;
		this.boardView.addResetButtonListener(new ResetController());

		for(int row = 0; row < rowNumber; row++){
			for(int col = 0; col < colNumber; col++){
				blocksData[row][col] = new RowBlock(row, col);
				this.boardView.addBlockButtonListener(new BlockController(), row, col);
			}

		}


	}

	/**
	 * Creates a new game initializing the GUI.
	 */
	public InARowController() {
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(new Dimension(500, 350));
		gui.setResizable(true);

		JPanel gamePanel = new JPanel(new FlowLayout());
		JPanel game = new JPanel(new GridLayout(3,3));
		gamePanel.add(game, BorderLayout.CENTER);

		JPanel options = new JPanel(new FlowLayout());
		options.add(reset);
		JPanel messages = new JPanel(new FlowLayout());
		messages.setBackground(Color.white);

		gui.add(gamePanel, BorderLayout.NORTH);
		gui.add(options, BorderLayout.CENTER);
		gui.add(messages, BorderLayout.SOUTH);

		messages.add(playerturn);
		playerturn.setText("Player 1 to play 'X'");

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetGame();
			}
		});

		// Initialize a JButton for each cell of the 3x3 game board.
		for(int row = 0; row<3; row++) {
			for(int column = 0; column<3 ;column++) {

				blocksData[row][column] = new RowBlock(row, column);
				// The last row contains the legal moves
				blocksData[row][column].setContents("");
				blocksData[row][column].setIsLegalMove(row == 2);
				blocks[row][column] = new JButton();
				blocks[row][column].setPreferredSize(new Dimension(75,75));
				updateBlock(row,column);
				game.add(blocks[row][column]);

				blocks[row][column].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						move((JButton)e.getSource());
					}
				});
			}
		}
	}

	/**
	 * Moves the current player into the given block.
	 *
	 * @param block The block to be moved to by the current player
	 */
	public void move(JButton block) {

		--movesLeft;

		if(movesLeft % 2 == 1) {
			playerturn.setText("'X': Player 1");
		} else{
			playerturn.setText("'O': Player 2");
		}

		if(player.equals("1")) {
			// Check whether player 1 won
			if(block==blocks[0][0]) {
				blocksData[0][0].setContents("X");
				blocksData[0][0].setIsLegalMove(false);
				updateBlock(0,0);
				player = "2";
				if(movesLeft<7) {
					if((blocksData[0][0].getContents().equals(blocksData[0][1].getContents()) &&
							blocksData[0][1].getContents().equals(blocksData[0][2].getContents())) ||
							(blocksData[0][0].getContents().equals(blocksData[1][0].getContents()) &&
									blocksData[1][0].getContents().equals(blocksData[2][0].getContents())) ||
							(blocksData[0][0].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[2][2].getContents()))) {
						playerturn.setText("Player 1 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[0][1]) {
				blocksData[0][1].setContents("X");
				blocksData[0][1].setIsLegalMove(false);
				updateBlock(0,1);
				player = "2";
				if(movesLeft<7) {
					if((blocksData[0][1].getContents().equals(blocksData[0][0].getContents()) &&
							blocksData[0][0].getContents().equals(blocksData[0][2].getContents())) ||
							(blocksData[0][1].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[2][1].getContents()))) {
						playerturn.setText("Player 1 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[0][2]) {
				blocksData[0][2].setContents("X");
				blocksData[0][2].setIsLegalMove(false);
				updateBlock(0,2);
				player = "2";
				if(movesLeft<7) {
					if((blocksData[0][2].getContents().equals(blocksData[0][1].getContents()) &&
							blocksData[0][1].getContents().equals(blocksData[0][0].getContents())) ||
							(blocksData[0][2].getContents().equals(blocksData[1][2].getContents()) &&
									blocksData[1][2].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[0][2].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[2][0].getContents()))) {
						playerturn.setText("Player 1 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[1][0]) {
				blocksData[1][0].setContents("X");
				blocksData[1][0].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[0][0].setIsLegalMove(true);
				updateBlock(1,0);
				updateBlock(0,0);
				player = "2";
				if(movesLeft<7) {
					if((blocksData[1][0].getContents().equals(blocksData[1][1].getContents()) &&
							blocksData[1][1].getContents().equals(blocksData[1][2].getContents())) ||
							(blocksData[1][0].getContents().equals(blocksData[0][0].getContents()) &&
									blocksData[0][0].getContents().equals(blocksData[2][0].getContents()))) {
						playerturn.setText("Player 1 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[1][1]) {
				blocksData[1][1].setContents("X");
				blocksData[1][1].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[0][1].setIsLegalMove(true);
				updateBlock(1,1);
				updateBlock(0,1);
				player = "2";
				if(movesLeft<7) {
					if((blocksData[1][1].getContents().equals(blocksData[1][0].getContents()) &&
							blocksData[1][0].getContents().equals(blocksData[1][2].getContents())) ||
							(blocksData[1][1].getContents().equals(blocksData[0][1].getContents()) &&
									blocksData[0][1].getContents().equals(blocksData[2][1].getContents())) ||
							(blocksData[1][1].getContents().equals(blocksData[0][0].getContents()) &&
									blocksData[0][0].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[1][1].getContents().equals(blocksData[0][2].getContents()) &&
									blocksData[0][2].getContents().equals(blocksData[2][0].getContents()))) {
						playerturn.setText("Player 1 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[1][2]) {
				blocksData[1][2].setContents("X");
				blocksData[1][2].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[0][2].setIsLegalMove(true);
				updateBlock(1,2);
				updateBlock(0,2);
				player = "2";
				if(movesLeft<7) {
					if((blocksData[1][2].getContents().equals(blocksData[0][2].getContents()) &&
							blocksData[0][2].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[1][2].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[1][0].getContents()))) {
						playerturn.setText("Player 1 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[2][0]) {
				blocksData[2][0].setContents("X");
				blocksData[2][0].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[1][0].setIsLegalMove(true);
				updateBlock(2,0);
				updateBlock(1,0);
				player = "2";
				if(movesLeft<7) {
					if((blocksData[2][0].getContents().equals(blocksData[2][1].getContents()) &&
							blocksData[2][1].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[2][0].getContents().equals(blocksData[1][0].getContents()) &&
									blocksData[1][0].getContents().equals(blocksData[0][0].getContents())) ||
							(blocksData[2][0].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[0][2].getContents()))) {
						playerturn.setText("Player 1 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[2][1]) {
				blocksData[2][1].setContents("X");
				blocksData[2][1].setIsLegalMove(false);
				// Enabled the space on top of this one
				blocksData[1][1].setIsLegalMove(true);
				updateBlock(2,1);
				updateBlock(1,1);
				player = "2";
				if(movesLeft<7) {
					if((blocksData[2][1].getContents().equals(blocksData[2][0].getContents()) &&
							blocksData[2][0].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[2][1].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[0][1].getContents()))) {
						playerturn.setText("Player 1 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[2][2]) {
				blocksData[2][2].setContents("X");
				blocksData[2][2].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[1][2].setIsLegalMove(true);
				updateBlock(2,2);
				updateBlock(1,2);
				player = "2";
				if(movesLeft<7) {
					if((blocksData[2][2].getContents().equals(blocksData[2][1].getContents()) &&
							blocksData[2][1].getContents().equals(blocksData[2][0].getContents())) ||
							(blocksData[2][2].getContents().equals(blocksData[1][2].getContents()) &&
									blocksData[1][2].getContents().equals(blocksData[0][2].getContents())) ||
							(blocksData[2][2].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[0][0].getContents()))) {
						playerturn.setText("Player 1 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			}
		} else {
			// Check whether player 2 won
			if(block==blocks[0][0]) {
				blocksData[0][0].setContents("O");
				blocksData[0][0].setIsLegalMove(false);
				updateBlock(0,0);
				player = "1";
				if(movesLeft<7) {
					if((blocksData[0][0].getContents().equals(blocksData[0][1].getContents()) &&
							blocksData[0][1].getContents().equals(blocksData[0][2].getContents())) ||
							(blocksData[0][0].getContents().equals(blocksData[1][0].getContents()) &&
									blocksData[1][0].getContents().equals(blocksData[2][0].getContents())) ||
							(blocksData[0][0].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[2][2].getContents()))) {
						playerturn.setText("Player 2 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[0][1]) {
				blocksData[0][1].setContents("O");
				blocksData[0][1].setIsLegalMove(false);
				updateBlock(0,1);
				player = "1";
				if(movesLeft<7) {
					if((blocksData[0][1].getContents().equals(blocksData[0][0].getContents()) &&
							blocksData[0][0].getContents().equals(blocksData[0][2].getContents())) ||
							(blocksData[0][1].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[2][1].getContents()))) {
						playerturn.setText("Player 2 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[0][2]) {
				blocksData[0][2].setContents("O");
				blocksData[0][2].setIsLegalMove(false);
				updateBlock(0,2);
				player = "1";
				if(movesLeft<7) {
					if((blocksData[0][2].getContents().equals(blocksData[0][1].getContents()) &&
							blocksData[0][1].getContents().equals(blocksData[0][0].getContents())) ||
							(blocksData[0][2].getContents().equals(blocksData[1][2].getContents()) &&
									blocksData[1][2].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[0][2].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[2][0].getContents()))) {
						playerturn.setText("Player 2 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[1][0]) {
				blocksData[1][0].setContents("O");
				blocksData[1][0].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[0][0].setIsLegalMove(true);
				updateBlock(1,0);
				updateBlock(0,0);
				player = "1";
				if(movesLeft<7) {
					if((blocksData[1][0].getContents().equals(blocksData[1][1].getContents()) &&
							blocksData[1][1].getContents().equals(blocksData[1][2].getContents())) ||
							(blocksData[1][0].getContents().equals(blocksData[0][0].getContents()) &&
									blocksData[0][0].getContents().equals(blocksData[2][0].getContents()))) {
						playerturn.setText("Player 2 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[1][1]) {
				blocksData[1][1].setContents("O");
				blocksData[1][1].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[0][1].setIsLegalMove(true);
				updateBlock(1,1);
				updateBlock(0,1);
				player = "1";
				if(movesLeft<7) {
					if((blocksData[1][1].getContents().equals(blocksData[1][0].getContents()) &&
							blocksData[1][0].getContents().equals(blocksData[1][2].getContents())) ||
							(blocksData[1][1].getContents().equals(blocksData[0][1].getContents()) &&
									blocksData[0][1].getContents().equals(blocksData[2][1].getContents())) ||
							(blocksData[1][1].getContents().equals(blocksData[0][0].getContents()) &&
									blocksData[0][0].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[1][1].getContents().equals(blocksData[0][2].getContents()) &&
									blocksData[0][2].getContents().equals(blocksData[2][0].getContents()))) {
						playerturn.setText("Player 2 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[1][2]) {
				blocksData[1][2].setContents("O");
				blocksData[1][2].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[0][2].setIsLegalMove(true);
				updateBlock(1,2);
				updateBlock(0,2);
				player = "1";
				if(movesLeft<7) {
					if((blocksData[1][2].getContents().equals(blocksData[0][2].getContents()) &&
							blocksData[0][2].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[1][2].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[1][0].getContents()))) {
						playerturn.setText("Player 2 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[2][0]) {
				blocksData[2][0].setContents("O");
				blocksData[2][0].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[1][0].setIsLegalMove(true);
				updateBlock(2,0);
				updateBlock(1,0);
				player = "1";
				if(movesLeft<7) {
					if((blocksData[2][0].getContents().equals(blocksData[2][1].getContents()) &&
							blocksData[2][1].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[2][0].getContents().equals(blocksData[1][0].getContents()) &&
									blocksData[1][0].getContents().equals(blocksData[0][0].getContents())) ||
							(blocksData[2][0].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[0][2].getContents()))) {
						playerturn.setText("Player 2 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[2][1]) {
				blocksData[2][1].setContents("O");
				blocksData[2][1].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[1][1].setIsLegalMove(true);
				updateBlock(2,1);
				updateBlock(1,1);
				player = "1";
				if(movesLeft<7) {
					if((blocksData[2][1].getContents().equals(blocksData[2][0].getContents()) &&
							blocksData[2][0].getContents().equals(blocksData[2][2].getContents())) ||
							(blocksData[2][1].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[0][1].getContents()))) {
						playerturn.setText("Player 2 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			} else if(block==blocks[2][2]) {
				blocksData[2][2].setContents("O");
				blocksData[2][2].setIsLegalMove(false);
				// Enable the space on top of this one
				blocksData[1][2].setIsLegalMove(true);
				updateBlock(2,2);
				updateBlock(1,2);
				player = "1";
				if(movesLeft<7) {
					if((blocksData[2][2].getContents().equals(blocksData[2][1].getContents()) &&
							blocksData[2][1].getContents().equals(blocksData[2][0].getContents())) ||
							(blocksData[2][2].getContents().equals(blocksData[1][2].getContents()) &&
									blocksData[1][2].getContents().equals(blocksData[0][2].getContents())) ||
							(blocksData[2][2].getContents().equals(blocksData[1][1].getContents()) &&
									blocksData[1][1].getContents().equals(blocksData[0][0].getContents()))) {
						playerturn.setText("Player 2 wins!");
						endGame();
					} else if(movesLeft==0) {
						playerturn.setText(GAME_END_NOWINNER);
					}
				}
			}
		}
	}

	/**
	 * Updates the block at the given row and column
	 * after one of the player's moves.
	 *
	 * @param row The row that contains the block
	 * @param column The column that contains the block
	 */
	protected void updateBlock(int row, int column) {
		blocks[row][column].setText(blocksData[row][column].getContents());
		blocks[row][column].setEnabled(blocksData[row][column].getIsLegalMove());
	}

	/**
	 * Ends the game disallowing further player turns.
	 */
	@
	public void endGame() {
		for(int row = 0;row<3;row++) {
			for(int column = 0;column<3;column++) {
				blocks[row][column].setEnabled(false);
			}
		}
	}



	final class ResetController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			/**
			 * Resets the game to be able to start playing again.
			 */

			for(int row = 0;row<3;row++) {
				for(int column = 0;column<3;column++) {
					blocksData[row][column].reset();
					// Enable the bottom row
					blocksData[row][column].setIsLegalMove(row == 2);
					updateBlock(row,column);
				}
			}
			player = "1";
			movesLeft = 9;
			playerturn.setText("Player 1 to play 'X'");

		}
	}

	final class BlockController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

}
