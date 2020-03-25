package view;

import java.awt.event.ActionListener;

/**
 * Created by chungyang on 2/12/20.
 */
public interface BoardButtonView extends BoardView{

    void addBlockButtonListener(ListenerAdapter listener, int row, int col);

    void removeBlockButtonListener(ListenerAdapter listener, int row, int col);

    Object getBlockButton(int row, int col);

    void setEnabled(boolean enabled, int row, int col);
}
