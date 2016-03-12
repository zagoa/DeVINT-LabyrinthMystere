package dvt.labyrinth.model;

import dvt.devint.Jeu;
import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Tile;
import dvt.labyrinth.Tray;
import dvt.labyrinth.actions.MovePlayerAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Time;

import static dvt.labyrinth.ConstantesLabyrinth.CASE_LENGTH;
import static dvt.labyrinth.ConstantesLabyrinth.NBRE_CASES;

/**
 * Created by user on 10/03/2016.
 */
public class Game1vs1 extends Jeu{
    // The 'world' : Labyrinth
    private JPanel world;
    // The tray of the labyrinth
    private Tray tray;
    // The players
    private Player player1;
    private Player player2;
    //The time counter
    private int time;


    //The constructor : a 1 vs 1 game (no IA)
    public Game1vs1(Tray tray, Player player1, Player player2){
        this.tray = tray;
        this.player1 = player1;
        this.player2 = player2;
        this.time=0;

    }

    public void startGame(){
        Tray tray = new Tray();
        Player player1 = new Player("Zago", new Pawn(ConstantesLabyrinth.RESSOURCES.ZAGO));
        Player player2 = new Player("Gerard", new Pawn(ConstantesLabyrinth.RESSOURCES.GERARD));
        Game1vs1 game = new Game1vs1(tray,player1,player2);
    }



    public Player getPlayerToPlay(){
        if(player1.getTimeToPlay()){ return player1; }
        return player2;
    }

    public void turnFinished() {
        this.time += 1;
        if (time % 2 == 0) {
            player1.setTimeToPlay(true);
            player2.setTimeToPlay(false);
        }
        if (time % 2 == 1) {
            player1.setTimeToPlay(false);
            player2.setTimeToPlay(true);
        }
    }



    @Override
    public void init() {
        world = new JPanel();
        world.setBackground(getBackground());

        this.add(world);

        world.setLayout(new GridBagLayout());

        tray = new Tray();

        // Start a game : so create the tray, players, time counter
        startGame();

        showTray();
    }


    @Override
    public void update() {
        checkMovePositions(getPlayerToPlay());
        //getPlayerToPlay().move(tray,);
        turnFinished();
    }

    @Override
    public void render() { world.setBackground(getBackground()); }

    @Override
    public void reset() { startGame(); }


    public void checkMovePositions(Player player) {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();

        Tile tile[][] = tray.getTray();
        if (player.canMove(tray, ConstantesLabyrinth.DIRECTIONS.LEFT))
            tile[y][x - CASE_LENGTH].setHighlighted(new Arrow(ConstantesLabyrinth.RESSOURCES.ARROW_LEFT)); // Left
        if (player.canMove(tray, ConstantesLabyrinth.DIRECTIONS.RIGHT))
            tile[y][x+CASE_LENGTH].setHighlighted(new Arrow(ConstantesLabyrinth.RESSOURCES.ARROW_RIGHT)); // Right
        if (player.canMove(tray, ConstantesLabyrinth.DIRECTIONS.FRONT))
            tile[y-CASE_LENGTH][x].setHighlighted(new Arrow(ConstantesLabyrinth.RESSOURCES.ARROW_UP)); // Down
        if (player.canMove(tray, ConstantesLabyrinth.DIRECTIONS.BACK))
            tile[y + CASE_LENGTH][x].setHighlighted(new Arrow(ConstantesLabyrinth.RESSOURCES.ARROW_DOWN)); // Up

        addControlUp(KeyEvent.VK_DOWN, new MovePlayerAction(this, ConstantesLabyrinth.DIRECTIONS.BACK));
        addControlUp(KeyEvent.VK_UP, new MovePlayerAction(this, ConstantesLabyrinth.DIRECTIONS.FRONT));
        addControlUp(KeyEvent.VK_LEFT, new MovePlayerAction(this, ConstantesLabyrinth.DIRECTIONS.LEFT));
        addControlUp(KeyEvent.VK_RIGHT, new MovePlayerAction(this, ConstantesLabyrinth.DIRECTIONS.RIGHT));
    }

    /**
     * Move the player
     *
     * @param d a direction where to move
     */
    public void movePlayer(Player player,ConstantesLabyrinth.DIRECTIONS d) {
        unHighlightAll(player);
        player.move(tray, d);
    }

    /**
     * Method to unlight all the tiles near the player
     */
    public void unHighlightAll(Player player) {
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

}
