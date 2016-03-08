package dvt.labyrinth.model.Tray;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by user on 02/03/2016.
 */
public class BackAction extends AbstractAction{

    private static final long serialVersionUID = 1L;
    private transient Tray tray;


    public BackAction(Tray tray) {
        this.tray = tray;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        tray.moveBack();
    }
}
