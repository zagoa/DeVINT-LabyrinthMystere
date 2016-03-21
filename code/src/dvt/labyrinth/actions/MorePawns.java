package dvt.labyrinth.actions;

import dvt.labyrinth.game.SelectPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 'Show more pawns' ACTION
 */
public class MorePawns implements ActionListener {
    public SelectPlayer sp;

    public MorePawns(SelectPlayer sp) {
        this.sp = sp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        sp.setNextIcons();
    }
}