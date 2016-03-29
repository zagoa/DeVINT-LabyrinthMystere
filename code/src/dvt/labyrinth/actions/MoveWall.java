package dvt.labyrinth.actions;

import dvt.labyrinth.tools.ConstantesLabyrinth.DIRECTIONS;
import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.game.Game;
import dvt.labyrinth.tools.ConstantesLabyrinth.VOCAL;

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
        if (!fenetre.otherPlayer().isBlocked(fenetre.getTray())) {
            fenetre.fillGap(dir, tile.getPosition());
            fenetre.nextTurn();
        }else{
            int x =tile.getPosition().getX();
            int y= tile.getPosition().getY();
            tile.clearTile();
            fenetre.getCurrentPlayer().setNbWall(fenetre.getCurrentPlayer().getNbWall()+1);
            fenetre.playText(VOCAL.BLOCK.toString());
            if(dir.equals(DIRECTIONS.FRONT)) fenetre.getTray().getTile(x,y+2).clearTile();
            if(dir.equals(DIRECTIONS.BACK)) fenetre.getTray().getTile(x,y-2).clearTile();
            if(dir.equals(DIRECTIONS.RIGHT)) fenetre.getTray().getTile(x-2,y).clearTile();
            if(dir.equals(DIRECTIONS.LEFT)) fenetre.getTray().getTile(x+2,y).clearTile();
        }
        fenetre.setSettingWall(false);
        fenetre.unHighlightAll();


    }


}



