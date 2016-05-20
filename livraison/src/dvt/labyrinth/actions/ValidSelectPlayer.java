package dvt.labyrinth.actions;

/**
 * Created by Adrian on 21/03/2016.
 */

import dvt.labyrinth.menu.SelectPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 'Valid the pawn and the name' ACTION
 */
public class ValidSelectPlayer implements ActionListener {
    private SelectPlayer sp;

    public ValidSelectPlayer(SelectPlayer sp) {
        this.sp = sp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        sp.validSelection();
    }
}