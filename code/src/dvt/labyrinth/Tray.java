package dvt.labyrinth;

import dvt.labyrinth.model.DefaultItem;

import static dvt.labyrinth.ConstantesLabyrinth.*;

/**
 * The tray of the game
 *
 * @author Thomas
 */
public class Tray {
    // A tray is an array of tile
    private Tile tray[][];

    /**
     * Default constructor
     */
    public Tray() {
        // Set the tray
        tray = new Tile[NBRE_CASES][NBRE_CASES];

        // Fill the tray
        for (int y = 0; y < NBRE_CASES; y++) {
            for (int x = 0; x < NBRE_CASES; x++)
                tray[y][x] = new Tile(new DefaultItem(), new Position(x,y));
        }
    }

    /**
     * Getter on the tray
     * @return The tray, an array of tile
     */
    public Tile[][] getTray() {
        return tray;
    }

    /**
     * Get a specific tile, base on its x and y coordinate
     *
     * @param x
     *          The x coordinate
     * @param y
     *          The y coordinate
     * @return The (x,y) tile
     */
    public Tile getTile(int x, int y) {
        return tray[y][x];
    }

    /**
     * Get a tile based on its Position.
     *
     * @param position
     *          The position of the Tile we want
     * @return The tile
     */
    public Tile getTile(Position position) {
        return getTile(position.getX(), position.getY());
    }
}