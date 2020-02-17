package view;

import model.BlockButton;

import java.awt.event.ActionListener;

/**
 * Created by chungyang on 2/18/20.
 */
public interface ResetButton extends BoardView{

    void addResetButtonListener(ActionListener listener);

    void removeResetListener(ActionListener listener);

    BlockButton getResetButton();
}
