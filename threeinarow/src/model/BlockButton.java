package model;

import javax.swing.*;

/**
 * Created by chungyang on 2/13/20.
 */
public class BlockButton extends JButton{

    private int row;
    private int col;

    public BlockButton(int row, int col){
        this.row = row;
        this.col = col;
    }

    public BlockButton(String buttonText){
        super(buttonText);
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.col;
    }
}
