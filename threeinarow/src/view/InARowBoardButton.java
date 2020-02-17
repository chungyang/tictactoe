package view;

import model.BlockButton;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Created by chungyang on 2/12/20.
 */
public class InARowBoardButton implements BoardButton, ResetButton, TextView {


    private JFrame gui = new JFrame("Three in a Row");
    private BlockButton[][] blocks = new BlockButton[3][3];
    private BlockButton reset = new BlockButton("Reset");
    private JTextArea playerturn = new JTextArea();

    public InARowBoardButton(int maxRowNumber, int maxColNumber){

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


        for(int row = 0; row < maxRowNumber; row++) {
            for (int col = 0; col < maxColNumber; col++) {
                blocks[row][col] = new BlockButton(row, col);
                blocks[row][col].setPreferredSize(new Dimension(75,75));
                game.add(blocks[row][col]);
            }
        }

        gui.setVisible(true);
    }

    public JTextArea getPlayerturn() {
        return playerturn;
    }

    @Override
    public BlockButton getResetButton(){
        return reset;
    }

    @Override
    public JFrame getGui(){
        return gui;
    }

    public BlockButton getBlockButton(int row, int col){
        return blocks[row][col];
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

    @Override
    public JTextArea getTextView() {
        return playerturn;
    }

    @Override
    public void setTextView(String text) {
        this.playerturn.setText(text);
    }
}
