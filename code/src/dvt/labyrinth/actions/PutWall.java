package dvt.labyrinth.actions;

import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static dvt.labyrinth.tools.ConstantesLabyrinth.VOCAL;

/**
 * An action : put a wall on a tile
 */
public class PutWall implements ActionListener {

    // The tile
    private Tile tile;
    // The game, where the tile is
    private Game lab;

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
        // We need to check if tile is a wall && disable the clic && is not already occupied
        if (lab.getCurrentPlayer().getNbWall() <= 0 && !lab.getCurrentPlayer().isABot()) {
            lab.playText(VOCAL.NOT_ENOUGTH_WALL.toString());
        } else if (tile.isAWall() && !lab.isSettingWall() && !tile.isOccupied() && lab.checkPutWall(tile.getPosition()) ) {
            tile.positionWall();
            if(!lab.otherPlayer().isBlocked(lab.getTray())) {
                lab.highlightWall(tile.getPosition());
                //décompte un mur au joueur
                lab.getCurrentPlayer().setNbWall(lab.getCurrentPlayer().getNbWall() - 1);
                if (!lab.getCurrentPlayer().isABot())
                    lab.playText(VOCAL.HUMAN_WALL.toString());
                else lab.playText(VOCAL.BOT_WALL.toString());
            }else{
                tile.clearTile();
                lab.playText("Tu ne peux pas bloquer le jeu");
            }

        } else if (!lab.checkPutWall(tile.getPosition()) || tile.isOccupied()) {
            lab.playText(VOCAL.ERROR_WALL.toString());
        }

    }
}

