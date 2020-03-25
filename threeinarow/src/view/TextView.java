package view;

import javax.swing.*;

/**
 * Created by chungyang on 2/18/20.
 */
public interface TextView extends BoardView{

    String getText();

    Object getTextView();

    void setTextView(String text);
}
