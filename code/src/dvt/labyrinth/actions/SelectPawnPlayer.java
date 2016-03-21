package dvt.labyrinth.actions;

import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.game.SelectPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 'Select a pawn' ACTION
 */
public class SelectPawnPlayer implements ActionListener {
    private ConstantesLabyrinth.RESSOURCES r;
    private SelectPlayer sp;

    public SelectPawnPlayer(SelectPlayer sp, ConstantesLabyrinth.RESSOURCES r) {
        this.r = r;
        this.sp = sp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sp.selectPawn(r, (JButton)e.getSource());
    }
}