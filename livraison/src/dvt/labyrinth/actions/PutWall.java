package dvt.labyrinth.actions;

import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.game.Game;
import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action : put a wall on a tile
 */
public class PutWall implements ActionListener {

    // The tile
    private Tile tile;
    // The game, where the tile is
    private Game lab;

    /**
     * Method from http://stackoverflow.com/questions/4722835/how-to-temporarily-disable-event-listeners-in-swing
     * @author Sean Patrick Floyd
     */
    private static boolean active = true;
    public static void setActive(boolean active){
        PutWall.active = active;
    }

    /**
     * Constructor
     *
     * @param lab  The game itself
     * @param tile The tile
     */
    public PutWall(Game lab, Tile tile) {
        this.tile = tile;
        this.lab = lab;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (active) {
            // We need to check if tile is a wall && disable the clic && is not already occupied
            //If the tile is not for a Wall
            if (lab.getCurrentPlayer().getNbWall() <= 0 && !lab.getCurrentPlayer().isABot()) {
                playText(lab.getSIVOX(), VOCAL.NOT_ENOUGTH_WALL.toString());
            } else if (tile.isAWall() && !lab.isSettingWall() && !tile.isOccupied() && ((lab.checkPutWall(tile.getPosition()) && config.get(CONFIG.WALL) > 0) || true)) {
                tile.positionWall();
                //if the position is correct and if the position don't block a player
                boolean gameNotBlocked = lab.gameNotBlocked(lab.getTray());

                if (gameNotBlocked) { // Game not blocked
                    //décompte un mur au joueur
                    lab.getCurrentPlayer().setNbWall(lab.getCurrentPlayer().getNbWall() - 1);

                    if (config.get(CONFIG.WALL) == 0) { // Walls are only one cell
                        lab.nextTurn();
                        lab.setSettingWall(false);
                    } else { // Walls are more than one cell
                        lab.highlightWall(tile.getPosition());
                        playText(lab.getSIVOX(), VOCAL.HUMAN_WALL.toString());

                    }
                } else { // Game blocked
                    tile.clearTile();
                    playText(lab.getSIVOX(), VOCAL.BLOCK.toString());
                }
            } else if (!lab.checkPutWall(tile.getPosition()) || tile.isOccupied()) {
                playText(lab.getSIVOX(), VOCAL.ERROR_WALL.toString());
            }
        }
    }
}

