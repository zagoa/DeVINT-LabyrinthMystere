package dvt.labyrinth;

import dvt.devint.Jeu;
import dvt.labyrinth.actions.MovePlayerAction;

import dvt.labyrinth.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static dvt.labyrinth.ConstantesLabyrinth.*;

/**
 * The Labyrinth class.
 * It's the game itself.
 *
 * @author Arnaud, Thomas, Etienne & Adrian
 */
public class Labyrinth extends Jeu {
    // The 'world' : Labyrinth
    private JPanel world;
    // The tray of the labyrinth
    private Tray tray;
    // The player
    private Player player;

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());

        this.add(world);

        world.setLayout(new GridBagLayout());

        tray = new Tray();

        // Keep players
        addPlayers();

        showTray();
    }

    @Override
    public void update() {
        checkMovePositions();
    }

    /**
     * Check where we can move, and highlight all the tiles (if we can move)
     */
    public void checkMovePositions() {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();

        Tile tile[][] = tray.getTray();
        if (player.canMove(tray, DIRECTIONS.LEFT))
            tile[y][x - CASE_LENGTH].setHighlighted(new Arrow(RESSOURCES.ARROW_LEFT)); // Left
        if (player.canMove(tray, DIRECTIONS.RIGHT))
            tile[y][x+CASE_LENGTH].setHighlighted(new Arrow(RESSOURCES.ARROW_RIGHT)); // Right
        if (player.canMove(tray, DIRECTIONS.FRONT))
            tile[y-CASE_LENGTH][x].setHighlighted(new Arrow(RESSOURCES.ARROW_UP)); // Down
        if (player.canMove(tray, DIRECTIONS.BACK))
            tile[y + CASE_LENGTH][x].setHighlighted(new Arrow(RESSOURCES.ARROW_DOWN)); // Up

        addControlUp(KeyEvent.VK_DOWN, new MovePlayerAction(this, DIRECTIONS.BACK));
        addControlUp(KeyEvent.VK_UP, new MovePlayerAction(this, DIRECTIONS.FRONT));
        addControlUp(KeyEvent.VK_LEFT, new MovePlayerAction(this, DIRECTIONS.LEFT));
        addControlUp(KeyEvent.VK_RIGHT, new MovePlayerAction(this, DIRECTIONS.RIGHT));
    }



    @Override
    public void render() {
        world.setBackground(getBackground());
    }

    @Override
    public void reset() {

    }

    /**
     * Display the grid/tray
     */
    public void showTray() {
        Tile tile[][] = tray.getTray();

        for (int y = 0; y < tile.length; y++) {
            for (int x = 0; x < tile[y].length; x++) {

                if (tile[y][x].isAWall()) { // Walls
                    tile[y][x].getComponent().setPreferredSize(new Dimension(10, ((y % 2 == 1) ? 10 : 50)));
                    tile[y][x].setListenerWall(this);
                }
                else // Move tiles
                    tile[y][x].getComponent().setPreferredSize(new Dimension(100, ((y % 2 == 1) ? 10 : 50)));

                world.add(tile[y][x].getComponent(), getGridBagConstraints(x,y));
            }
        }
    }

    /**
     * Display process (GridBagLayout)
     *
     * @param x
     *          The x coordinate (grid)
     * @param y
     *          The y coordinate (grid)
     *
     * @return the constraint (layout)
     */
    public GridBagConstraints getGridBagConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = x;
        c.gridy = y;

        if (y % 2 == 1) // Wall
            c.gridwidth = (x % 2 == 0) ? 2 : 1; // Remove the small square
        else
            c.gridwidth = 1;

        c.gridheight = 1;

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;

        return c;
    }

    /**
     * Create players
     */
    public void addPlayers() {
        player = new Player("Bernard", new Pawn(RESSOURCES.THEO), 0, true);
        player.setPos(new Position(8, 10), tray);
    }

    /**
     * Method to unlight all the tiles near the player
     */
    public void unHighlightAll() {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();

        if (x-CASE_LENGTH >= 0 && tray.getTile(x-CASE_LENGTH, y).isHighlighted()) // Left is highlighted?
            tray.getTile(x-CASE_LENGTH, y).unHighlight();

        if (x+CASE_LENGTH <= NBRE_CASES-1 && tray.getTile(x+CASE_LENGTH, y).isHighlighted()) // Right is highlighted?
            tray.getTile(x+CASE_LENGTH, y).unHighlight();

        if (y-CASE_LENGTH >= 0 && tray.getTile(x, y-CASE_LENGTH).isHighlighted()) // Up is highlighted?
            tray.getTile(x, y-CASE_LENGTH).unHighlight();

        if (y+CASE_LENGTH <= NBRE_CASES-1 && tray.getTile(x, y+CASE_LENGTH).isHighlighted()) // Back is highlighted?
            tray.getTile(x, y+CASE_LENGTH).unHighlight();
    }

    /**
     * Move the player
     *
     * @param d a direction where to move
     */
    public void movePlayer(DIRECTIONS d) {
        unHighlightAll();
        player.move(tray, d);
    }
}
