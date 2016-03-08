package dvt.labyrinth.model.Tray;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by user on 02/03/2016.
 */
public class RightAction extends AbstractAction{
    private static final long serialVersionUID = 1L;
    private transient Tray tray;


    public RightAction(Tray tray) {
        this.tray = tray;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        tray.moveRight();
    }
}
