package view;

import model.BlockButton;

import java.awt.event.ActionListener;

/**
 * Created by chungyang on 2/12/20.
 */
public interface BoardButtonView extends BoardView{

    void addBlockButtonListener(ActionListener listener, int row, int col);

    void removeBlockButtonListener(ActionListener listener, int row, int col);

    BlockButton getBlockButton(int row, int col);

    void setEnabled(boolean enabled, int row, int col);
}
