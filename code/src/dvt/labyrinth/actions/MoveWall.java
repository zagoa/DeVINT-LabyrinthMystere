package dvt.labyrinth.actions;

import dvt.labyrinth.game.Game;
import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.tools.ConstantesLabyrinth.DIRECTIONS;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Arnaud on 16/03/2016.
 */
public class MoveWall extends AbstractAction {
    // The game
    private transient Game fenetre;
    // The direction of the movement
    private Tile tile;

    private DIRECTIONS dir;

    public MoveWall(Game fenetre, Tile tile, DIRECTIONS dir) {
        this.fenetre = fenetre;
        this.tile = tile;
        this.dir = dir;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // We need to check if tile is a wall
        fenetre.getSIVOX().stop();
        tile.positionWall();
        //if (!fenetre.otherPlayer().isBlocked(fenetre.getTray()) || !fenetre.getCurrentPlayer().isBlocked(fenetre.getTray())) {
        fenetre.fillGap(dir, tile.getPosition());
        fenetre.nextTurn();
        fenetre.setSettingWall(false);
        fenetre.unHighlightAll();


    }


}



