package dvt.labyrinth.model.essential;

import dvt.labyrinth.tools.Position;

import java.awt.*;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

/**
 * The tray of the game
 *
 * @author Thomas, Zago
 */
public class Tray {
    // A tray is an array of tile
    private Tile tray[][];
    private Color trayColor;
    private int borderSizeLevel;

    /**
     * Default constructor
     */
    public Tray() {
        trayColor = DEFAULT_TRAY_COLOR;
        borderSizeLevel = DEFAULT_SIZE_LEVEL;

        // Set the tray
        tray = new Tile[config.get(CONFIG.LENGTH)][config.get(CONFIG.LENGTH)];

        // Fill the tray
        for (int y = 0; y < config.get(CONFIG.LENGTH); y++) {
            for (int x = 0; x < config.get(CONFIG.LENGTH); x++)
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

    /**
     * Check if we can set a wall on a tile
     *
     * @param d
     *          The direction where to set
     * @param position
     *          The position of the tile
     *
     * @return true / false (we can / we can not)
     */
    public boolean canSetAWall(DIRECTIONS d, Position position){
        int x = position.getX();
        int y = position.getY();

        switch (d) {
            case FRONT:
                return (y-CASE_LENGTH >= 0
                        && !this.getTile(x, y-CASE_LENGTH).isOccupied()
                        && !this.getTile(x, y-1).isOccupied()); // In map && wall not present && tile not occupied

            case BACK:
                return (y+CASE_LENGTH <= config.get(CONFIG.LENGTH)-1
                        && !this.getTile(x, y+CASE_LENGTH).isOccupied()
                        && !this.getTile(x, y+1).isOccupied()); // In map && wall not present && tile not occupied

            case RIGHT:
                return (x+CASE_LENGTH <= config.get(CONFIG.LENGTH)-1
                        && !this.getTile(x+CASE_LENGTH, y).isOccupied()
                        && !this.getTile(x+1, y).isOccupied()); // In map && wall not present && tile not occupied

            case LEFT:
                return (x-CASE_LENGTH >= 0
                        && !this.getTile(x-config.get(CONFIG.LENGTH), y).isOccupied()
                        && !this.getTile(x-1, y).isOccupied()); // In map && wall not present && tile not occupied

            default:
                return false;
        }

    }

    public void changeColor(Color color) {
        trayColor = color;

        for (int y = 0; y < config.get(CONFIG.LENGTH); y++) {
            for (int x = 0; x < config.get(CONFIG.LENGTH); x++)
                tray[y][x].setBorderColor(trayColor);
        }
    }

    public void changeBorderSize(int level) {
        int size;

        switch (level) {
            case 50:
                size = 4;
                break;
            case 60:
                size = 5;
                break;
            case 70:
                size = 6;
                break;
            case 90:
                size = 8;
                break;

            default:
                size = 2;
                break;
        }

        for (int y = 0; y < config.get(CONFIG.LENGTH); y++) {
            for (int x = 0; x < config.get(CONFIG.LENGTH); x++)
                tray[y][x].setBorderSize(size);
        }
    }

    public Color getTrayColor() {
        return trayColor;
    }

    public int getBorderSizeLevel() {
        return borderSizeLevel;
    }
}