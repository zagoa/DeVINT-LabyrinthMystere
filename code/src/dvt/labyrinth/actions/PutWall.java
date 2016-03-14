package dvt.labyrinth.actions;

import dvt.labyrinth.game.TwoPlayers;
import dvt.labyrinth.Tile;
import dvt.labyrinth.model.Game1vs1;
import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Wall;
import javafx.geometry.Pos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action : put a wall on a tile
 */
public class PutWall implements ActionListener {

    // The tile
    private Tile tile;
    // The game, where the tile is
    private TwoPlayers lab;

    /**
     * Constructor
     *
     * @param lab
     *          The game itself
     * @param tile
     *          The tile
     */
    public PutWall(TwoPlayers lab, Tile tile){
        this.tile = tile;
        this.lab = lab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // We need to check if tile is a wall
        if (tile.isAWall()) {
            tile.putWall();
            lab.unHighlightAll(); // If we've just blocked the player
        }
    }
}

