package dvt.labyrinth.actions;

import dvt.labyrinth.ConstantesLabyrinth.DIRECTIONS;
import dvt.labyrinth.Tile;
import dvt.labyrinth.game.TwoPlayers;
import dvt.labyrinth.model.Wall;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Arnaud on 16/03/2016.
 */
public class MoveWall extends AbstractAction {
    // The game
    private transient TwoPlayers fenetre;
    // The direction of the movement
    private Tile tile;

    public MoveWall(TwoPlayers fenetre, Tile tile) {
        this.fenetre = fenetre;
        this.tile = tile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // We need to check if tile is a wall
        tile.putWall();
        fenetre.setSettingWall(false);
        fenetre.unHighlightAll();
    }
}



