package dvt.labyrinth.actions;

import dvt.labyrinth.Tile;
import dvt.labyrinth.game.Game;

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
            lab.getSIVOX().playText("Tu n'as plus de murs disponibles");
        } else if (tile.isAWall() && !lab.isSettingWall() && !tile.isOccupied() && lab.checkPutWall(tile.getPosition())) {
            tile.positionWall();
            lab.highlightWall(tile.getPosition());
            //décompte un mur au joueur
            lab.getCurrentPlayer().setNbWall(lab.getCurrentPlayer().getNbWall() - 1);
            if (!lab.getCurrentPlayer().isABot())
                lab.getSIVOX().playText("Un mur a été posé, choisi là où le deuxième mur doit être, à l'aide des fléches directionnels");
            else lab.getSIVOX().playText("Le bot vient de poser un mur");

        } else if (!lab.checkPutWall(tile.getPosition()) || tile.isOccupied()) {
            lab.getSIVOX().playText("Impossible de poser un mur ici ! ");
        }

    }
}

