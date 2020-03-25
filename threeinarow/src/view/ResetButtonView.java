package view;

import java.awt.event.ActionListener;

/**
 * Created by chungyang on 2/18/20.
 */
public interface ResetButtonView extends BoardView{

    void addResetButtonListener(ListenerAdapter listener);

    void removeResetListener(ListenerAdapter listener);

    Object getResetButton();
}
