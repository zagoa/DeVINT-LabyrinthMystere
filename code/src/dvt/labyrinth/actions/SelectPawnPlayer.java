package dvt.labyrinth.actions;

import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.game.SelectPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 'Select a pawn' ACTION
 */
public class SelectPawnPlayer implements ActionListener {
    private ConstantesLabyrinth.RESOURCES r;
    private SelectPlayer sp;

    public SelectPawnPlayer(SelectPlayer sp, ConstantesLabyrinth.RESOURCES r) {
        this.r = r;
        this.sp = sp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sp.selectPawn(r, (JButton)e.getSource());
    }
}