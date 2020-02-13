package view;

import java.awt.event.ActionListener;

/**
 * Created by chungyang on 2/12/20.
 */
public interface BoardView {

    void addResetButtonListener(ActionListener listener);

    void removeResetListener(ActionListener listener);

    void addBlockButtonListener(ActionListener listener, int row, int col);

    void removeBlockButtonListener(ActionListener listener, int row, int col);


}
