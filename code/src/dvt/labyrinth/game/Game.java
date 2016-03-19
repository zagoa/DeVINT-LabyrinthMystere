package dvt.labyrinth.game;

import dvt.devint.Jeu;
import dvt.labyrinth.Position;
import dvt.labyrinth.Tile;
import dvt.labyrinth.Tray;
import dvt.labyrinth.actions.MovePlayerAction;
import dvt.labyrinth.actions.MoveWall;
import dvt.labyrinth.model.Arrow;
import dvt.labyrinth.model.Pawn;
import dvt.labyrinth.model.Player;
import dvt.labyrinth.model.Target;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

import static dvt.labyrinth.ConstantesLabyrinth.*;

/**
 * The Labyrinth class.
 * It's the game itself.
 *
 * @author Arnaud, Thomas, Etienne & Adrian
 */
public class Game extends Jeu {
    // The 'world' : Labyrinth
    private JPanel world;
    // The tray of the labyrinth
    private Tray tray;
    // Old players
    private HashMap<String, RESSOURCES> oldPlayers;
    // The players
    private Player[] players;
    // The current player
    private Player currentPlayer;
    // State of the game
    private boolean settingWall;

    public Game(HashMap<String, RESSOURCES> players) {
        super();

        addPlayers(players);
    }

    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());

        this.add(world);

        tray = new Tray();

        world.setLayout(new GridBagLayout());

        settingWall = false;

        showTray();
    }

    @Override
    public void update() {
        if (currentPlayer.hasWon())
            win();

        if (currentPlayer.isABot()) // Is a bot ?
            botPlay();
        else if (!settingWall) // Is not a bot and we're not setting walls
            checkMovePositions();
    }

    private void botPlay() {
        if (currentPlayer.canMove(tray, DIRECTIONS.FRONT))
            movePlayer(DIRECTIONS.FRONT);
        else if (currentPlayer.canMove(tray, DIRECTIONS.LEFT))
            movePlayer(DIRECTIONS.LEFT);
        else if (currentPlayer.canMove(tray, DIRECTIONS.RIGHT))
            movePlayer(DIRECTIONS.RIGHT);
        else if (currentPlayer.canMove(tray, DIRECTIONS.BACK))
            movePlayer(DIRECTIONS.BACK);
    }

    /**
     * Check where we can move, and highlight all the tiles (if we can move)
     */
    public void checkMovePositions() {
        int x = currentPlayer.getPosition().getX();
        int y = currentPlayer.getPosition().getY();

        Tile tile[][] = tray.getTray();
        if (currentPlayer.canMove(tray, DIRECTIONS.LEFT))
            tile[y][x - CASE_LENGTH].setHighlighted(new Arrow(RESSOURCES.ARROW_LEFT)); // Left
        if (currentPlayer.canMove(tray, DIRECTIONS.RIGHT))
            tile[y][x+CASE_LENGTH].setHighlighted(new Arrow(RESSOURCES.ARROW_RIGHT)); // Right
        if (currentPlayer.canMove(tray, DIRECTIONS.FRONT))
            tile[y-CASE_LENGTH][x].setHighlighted(new Arrow(RESSOURCES.ARROW_UP)); // Down
        if (currentPlayer.canMove(tray, DIRECTIONS.BACK))
            tile[y + CASE_LENGTH][x].setHighlighted(new Arrow(RESSOURCES.ARROW_DOWN)); // Up

        setTarget();

        addControlUp(KeyEvent.VK_DOWN   , new MovePlayerAction(this, DIRECTIONS.BACK));
        addControlUp(KeyEvent.VK_UP     , new MovePlayerAction(this, DIRECTIONS.FRONT));
        addControlUp(KeyEvent.VK_LEFT   , new MovePlayerAction(this, DIRECTIONS.LEFT));
        addControlUp(KeyEvent.VK_RIGHT  , new MovePlayerAction(this, DIRECTIONS.RIGHT));
    }

    /**
     * Check where we can put an other wall
     *
     * @param position
     *          The position of the wall
     */
    public void checkWall(Position position) {
        settingWall = true;

        int x = position.getX();
        int y = position.getY();

        unHighlightAll();

        // Check right && On the horizontal line
        if (tray.canSetAWall(DIRECTIONS.RIGHT, position) && y % 2 == 1) {
            addControlUp(KeyEvent.VK_RIGHT, new MoveWall(this, tray.getTile(x + 2, y), DIRECTIONS.RIGHT));
            tray.getTile(x + 2, y).setHighlighted(new Arrow(RESSOURCES.ARROW_SMALL_RIGHT));
        }
        // Check left && On the horizontal line
        if (tray.canSetAWall(DIRECTIONS.LEFT, position) && y % 2 == 1) {
            addControlUp(KeyEvent.VK_LEFT, new MoveWall(this, tray.getTile(x - 2, y), DIRECTIONS.LEFT));
            tray.getTile(x - 2, y).setHighlighted(new Arrow(RESSOURCES.ARROW_SMALL_LEFT));
        }
        // Check up && On the vertical line
        if (tray.canSetAWall(DIRECTIONS.FRONT, position) && y % 2 != 1) {
            addControlUp(KeyEvent.VK_UP, new MoveWall(this, tray.getTile(x, y - 2), DIRECTIONS.FRONT));
            tray.getTile(x, y - 2).setHighlighted(new Arrow(RESSOURCES.ARROW_SMALL_UP));
        }
        // Check down && On the vertical line
        if (tray.canSetAWall(DIRECTIONS.BACK, position) && y % 2 != 1) {
            addControlUp(KeyEvent.VK_DOWN, new MoveWall(this, tray.getTile(x, y + 2), DIRECTIONS.BACK));
            tray.getTile(x, y + 2).setHighlighted(new Arrow(RESSOURCES.ARROW_SMALL_DOWN));
        }
    }


    /**
     * Set a display target where to go
     */
    public void setTarget() {
        Tile tile[] = tray.getTray()[currentPlayer.getWonY()];

        for (int x = 0; x < tile.length; x+=2) {
            if (!tile[x].isOccupied())
                tile[x].setHighlighted(new Target(), Color.WHITE);
        }
    }

    /**
     * swip the turn of the currentPlayer
     */
    public void nextTurn() {
        currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];

    }

    @Override
    public void render() {
        world.setBackground(getBackground());
        // TODO : changer la valeur de la couleur des borders quand fond noir
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
                    tile[y][x].getComponent().setPreferredSize(new Dimension(10,(y%2 == 1) ? 10 : 50));

                    if ((tile[y][x].getPosition().getX()%2 != 1 || tile[y][x].getPosition().getY()%2 != 1)
                            && !tile[y][x].isOccupied())// Don't click the small square
                        tile[y][x].setListenerWall(this);
                }
                else // 'Moves' tiles
                    tile[y][x].getComponent().setPreferredSize(new Dimension(100, 50));

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
    public void addPlayers(HashMap<String, RESSOURCES> p) {
        players = new Player[p.size()];

        int k = 0;
        for (HashMap.Entry<String, RESSOURCES> e : p.entrySet())
            players[k++] = new Player(e.getKey(), new Pawn(e.getValue()), ((k%2 == 0) ? POSITIONS.TOP : POSITIONS.BOTTOM).getPos(), tray, (e.getValue() == RESSOURCES.BOT));

        currentPlayer = players[new Random().nextInt(1)];

        getSIVOX().playText(currentPlayer.getName()+" commence à jouer !");
        pause(1500);
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            return;
        }
    }

    /**
     * Method to unlight all the tiles near the player
     */
    public void unHighlightAll() {
        for (int y = 0; y < tray.getTray().length; y++) {
            for (int x = 0; x < tray.getTray()[y].length; x++) {
                if (tray.getTray()[y][x].isHighlighted())
                    tray.getTray()[y][x].unHighlight();
            }
        }
    }

    /**
     * Move the player
     *
     * @param d a direction where to move
     */
    public void movePlayer(DIRECTIONS d) {
        unHighlightAll();
        if (currentPlayer.move(tray, d)) { // has moved
            if (currentPlayer.hasWon())
                return;

            currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
        }
    }

    /**
     * Set a winner, with the specific view
     */
    public void win() {
        this.dispose(); // Delete this view
        new WinGame(currentPlayer).loop(); // Add win view
    }


    /**
     * We are (or not) setting wall
     *
     * @param settingWall
     *          We are / we are not (true / false)
     */
    public void setSettingWall(boolean settingWall) {
        this.settingWall = settingWall;
    }

    /**
     * Getter on 'are we setting walls?'
     * @return true / false
     */
    public boolean isSettingWall() {
        return settingWall;
    }

    /**
     * Set the small square as a wall too
     *
     * @param gap
     *          The direction where is the gap
     * @param position
     *          The original position
     */
    public void fillGap(DIRECTIONS gap, Position position){
        int x = position.getX();
        int y = position.getY();

        switch(gap){
            case RIGHT:
                tray.getTile(x-1,y).putWall(gap);
                break;

            case LEFT:
                tray.getTile(x+1,y).putWall(gap);
                break;

            case FRONT:
                tray.getTile(x,y+1).putWall(gap);
                break;

            case BACK:
                tray.getTile(x,y-1).putWall(gap);
                break;

            default:
                break;
        }
    }


}
