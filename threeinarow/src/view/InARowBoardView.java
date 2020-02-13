package view;

import model.BlockButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by chungyang on 2/12/20.
 */
public class InARowBoardView implements BoardView {

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

    private JFrame gui = new JFrame("Three in a Row");
    private BlockButton[][] blocks = new BlockButton[3][3];
    private BlockButton reset = new BlockButton("Reset");
    private JTextArea playerturn = new JTextArea();

    public InARowBoardView(){

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
        playerturn.setText("Player 1 to play " + Player.PLAYER_1.getMark());

    }


    @Override
    public void addResetButtonListener(ActionListener listener) {
        reset.addActionListener(listener);
    }

    @Override
    public void removeResetListener(ActionListener listener) {
        reset.removeActionListener(listener);
    }

    @Override
    public void addBlockButtonListener(ActionListener listener, int row, int col) {
        blocks[row][col].addActionListener(listener);
    }

    @Override
    public void removeBlockButtonListener(ActionListener listener, int row, int col) {
        blocks[row][col].removeActionListener(listener);
    }

}
