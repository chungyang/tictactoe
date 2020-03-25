package view;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by chungyang on 2/12/20.
 */
public class ThreeInARowBoardView implements BoardButtonView, ResetButtonView, TextView {


    private JFrame gui = new JFrame("Three in a Row");
    private BlockButton[][] blocks;
    private BlockButton reset = new BlockButton("Reset");
    private JTextArea playerturn = new JTextArea();

    public ThreeInARowBoardView(int rowSize, int columnSize){

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(rowSize,columnSize));
        gamePanel.add(game, BorderLayout.CENTER);

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        messages.add(playerturn);
        playerturn.setText("Player 1 to play " + Player.PLAYER_1.getMarker());

        blocks = new BlockButton[rowSize][columnSize];

        for(int row = 0; row < rowSize; row++) {
            for (int col = 0; col < columnSize; col++) {
                blocks[row][col] = new BlockButton(row, col);
                blocks[row][col].setPreferredSize(new Dimension(75,75));
                game.add(blocks[row][col]);
            }
        }

        gui.setVisible(true);
    }

    @Override
    public BlockButton getResetButton(){
        return reset;
    }


    public BlockButton getBlockButton(int row, int col){
        return blocks[row][col];
    }

    @Override
    public void addResetButtonListener(ListenerAdapter listener) {

        reset.addActionListener((e)->listener.performAction(e.getSource()));
    }

    @Override
    public void removeResetListener(ListenerAdapter listener) {
        reset.removeActionListener((e)->listener.performAction(e.getSource()));
    }

    @Override
    public void addBlockButtonListener(ListenerAdapter listener, int row, int col) {
        blocks[row][col].addActionListener((e)->listener.performAction(e.getSource()));
    }

    @Override
    public void removeBlockButtonListener(ListenerAdapter listener, int row, int col) {
        blocks[row][col].removeActionListener((e)->listener.performAction(e.getSource()));
    }

    @Override
    public String getText() {
        return this.playerturn.getText();
    }

    @Override
    public Object getTextView() {
        return playerturn;
    }

    @Override
    public void setTextView(String text) {
        this.playerturn.setText(text);
    }

    @Override
    public void setEnabled(boolean enabled, int row, int col){
        blocks[row][col].setEnabled(enabled);
    }

    @Override
    public void setVisible(boolean isVisible) {
        gui.setVisible(isVisible);
    }
}
