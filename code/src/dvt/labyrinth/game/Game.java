package dvt.labyrinth.game;

import dvt.devint.Jeu;
import dvt.labyrinth.actions.MovePlayerAction;
import dvt.labyrinth.actions.MoveWall;
import dvt.labyrinth.model.essential.*;
import dvt.labyrinth.model.player.*;
import dvt.labyrinth.tools.ConstantesLabyrinth;
import dvt.labyrinth.tools.Position;
import dvt.run.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;


/**
 * The Labyrinth class.
 * It's the game itself.
 *
 * @author Arnaud, Thomas, Etienne & Adrian
 */
public class Game extends Jeu {
    // The 'world' : Labyrinth
    protected JPanel world;
    // The tray of the labyrinth
    protected Tray tray;
    // Old players
    protected HashMap<String, RESOURCES> oldPlayers;
    // The players
    protected Player[] players;
    // The current player
    protected Player currentPlayer;
    // State of the game
    protected boolean settingWall;
    // Difficulty of the bot
    private DIFFICULTY botDifficulty = null;

    public static Clip clip;

    // TODO : (IA Medium) UN ROBOT NE DOIT PAS POUVOIR BLOQUER LE JEU

    public static synchronized void playSound() {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(RESOURCES.SOUND.getPath()).getAbsoluteFile());
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public Game(HashMap<String, RESOURCES> players) {
        super();

        addPlayers(players);
        playSound();
    }

    public Game(HashMap<String, RESOURCES> players, DIFFICULTY botDifficulty){
        super();
        this.botDifficulty = botDifficulty;
        addPlayerAndBot(players);

        playSound();
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

        if (currentPlayer.isABot()) { // Is a bot ?
            if (((IA)currentPlayer).getType() == DIFFICULTY.FACILE)
                moveIAEasyPlayer();
            else
                moveIAPlayer(null, otherPlayer().getPosition());

        }
        else if (!settingWall) // Is not a bot and we're not setting walls
            checkMovePositions();
    }

    @Override
    public void render() {
        world.setBackground(getBackground());

        if (getBackground() == Color.BLACK && tray.getTrayColor() != Color.WHITE)
            tray.changeColor(Color.WHITE);
        else if (getBackground() != Color.BLACK && tray.getTrayColor() != Color.BLACK)
            tray.changeColor(Color.BLACK);

        /**
         * SIZE: 40, 50, 60, 70, 90
         */

        int s = getFont().getSize();

        if (s != tray.getBorderSizeLevel())
            tray.changeBorderSize(s);

        // TODO : changer la valeur de la couleur des borders quand fond noir
    }

    @Override
    public void reset() {
    }


    /**
     * Check where we can move, and highlight all the tiles (if we can move)
     */
    public void checkMovePositions() {
        int x = currentPlayer.getPosition().getX();
        int y = currentPlayer.getPosition().getY();

        Tile tile[][] = tray.getTray();
        if (currentPlayer.canMove(DIRECTIONS.LEFT))
            tile[y][x - CASE_LENGTH].setHighlighted(new Arrow(RESOURCES.ARROW_LEFT)); // Left
        if (currentPlayer.canMove(DIRECTIONS.RIGHT))
            tile[y][x + CASE_LENGTH].setHighlighted(new Arrow(RESOURCES.ARROW_RIGHT)); // Right
        if (currentPlayer.canMove(DIRECTIONS.FRONT))
            tile[y - CASE_LENGTH][x].setHighlighted(new Arrow(RESOURCES.ARROW_UP)); // Down
        if (currentPlayer.canMove(DIRECTIONS.BACK))
            tile[y + CASE_LENGTH][x].setHighlighted(new Arrow(RESOURCES.ARROW_DOWN)); // Up

        setTarget();

        addControlUp(KeyEvent.VK_DOWN, new MovePlayerAction(this, DIRECTIONS.BACK));
        addControlUp(KeyEvent.VK_UP, new MovePlayerAction(this, DIRECTIONS.FRONT));
        addControlUp(KeyEvent.VK_LEFT, new MovePlayerAction(this, DIRECTIONS.LEFT));
        addControlUp(KeyEvent.VK_RIGHT, new MovePlayerAction(this, DIRECTIONS.RIGHT));
    }

    /**
     * @param tray
     * @return if the the game is blocked or not
     */
    public boolean gameNotBlocked(Tray tray){
        if(!otherPlayer().isBlocked() && !currentPlayer.isBlocked()) return true;
        return false;
    }

    /**
     * Check where we can put an other wall
     *
     * @param position The position of the wall
     */
    public void highlightWall(Position position) {
        settingWall = true;

        int x = position.getX();
        int y = position.getY();
        int i =0;
        unHighlightAll();
        setTarget();


        // Check right && On the horizontal line
        if (tray.canSetAWall(DIRECTIONS.RIGHT, position) && y % 2 == 1) {
            tray.getTile(x+2,y).positionWall();
            if(gameNotBlocked(tray)) {
                tray.getTile(x+2,y).clearTile();
                tray.getTile(x+2,y).getComponent().addActionListener(new MoveWall(this, tray.getTile(x+2, y ),tray.getTile(x,y), DIRECTIONS.RIGHT));
                tray.getTile(x + 2, y).setHighlighted(new Arrow(RESOURCES.ARROW_SMALL_RIGHT));
                i++;
            }else tray.getTile(x+2,y).clearTile();


        }
        // Check left && On the horizontal line
        if (tray.canSetAWall(DIRECTIONS.LEFT, position) && y % 2 == 1) {
            tray.getTile(x-2,y).positionWall();
            if(gameNotBlocked(tray)) {
                tray.getTile(x-2,y).clearTile();
                tray.getTile(x-2,y).getComponent().addActionListener(new MoveWall(this, tray.getTile(x-2, y),tray.getTile(x,y), DIRECTIONS.LEFT));
                tray.getTile(x - 2, y).setHighlighted(new Arrow(RESOURCES.ARROW_SMALL_LEFT));
                i++;
            }else tray.getTile(x-2,y).clearTile();

        }
        // Check up && On the vertical line
        if (tray.canSetAWall(DIRECTIONS.FRONT, position) && y % 2 != 1) {
            tray.getTile(x,y-2).positionWall();
            if(gameNotBlocked(tray)) {
                tray.getTile(x,y-2).clearTile();
                tray.getTile(x,y-2).getComponent().addActionListener(new MoveWall(this, tray.getTile(x, y - 2),tray.getTile(x,y), DIRECTIONS.FRONT));
                tray.getTile(x, y - 2).setHighlighted(new Arrow(RESOURCES.ARROW_SMALL_UP));
                i++;
            }else tray.getTile(x,y-2).clearTile();
        }
        // Check down && On the vertical line
        if (tray.canSetAWall(DIRECTIONS.BACK, position) && y % 2 != 1) {
            tray.getTile(x,y+2).positionWall();
            if(gameNotBlocked(tray)) {
                tray.getTile(x,y+2).clearTile();
                tray.getTile(x,y+2).getComponent().addActionListener(new MoveWall(this, tray.getTile(x, y + 2),tray.getTile(x,y), DIRECTIONS.BACK));
                tray.getTile(x, y + 2).setHighlighted(new Arrow(RESOURCES.ARROW_SMALL_DOWN));
                i++;
            }else tray.getTile(x,y+2).clearTile();
        }
        //Gestion d'un cas d'erreur
        if(i==0){
                tray.getTile(x,y).clearTile();
                currentPlayer.setNbWall(currentPlayer.getNbWall()+1);
                setSettingWall(false);
                ConstantesLabyrinth.playText(getSIVOX(),VOCAL.BLOCK);
            }


    }

    public boolean checkPutWall(Position position) {
        int y = position.getY();
        return (tray.canSetAWall(DIRECTIONS.RIGHT, position) && y % 2 == 1
                || tray.canSetAWall(DIRECTIONS.LEFT, position) && y % 2 == 1
                || tray.canSetAWall(DIRECTIONS.FRONT, position) && y % 2 != 1
                || tray.canSetAWall(DIRECTIONS.BACK, position) && y % 2 != 1);

    }

    /**
     * Set a display target where to go
     */
    public void setTarget() {
        Tile tile[] = tray.getTray()[currentPlayer.getWonY()];

        for (int x = 0; x < tile.length; x += 2) {
            if (!tile[x].isOccupied())
                tile[x].setHighlighted(new Target(), Color.WHITE);
        }
    }

    /**
     * swip the turn of the currentPlayer
     */
    public void nextTurn() {
        currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];

        if (botDifficulty != null) { // 1 players
            if (!currentPlayer.isABot())
                playText(getSIVOX(), parse(getRandomVocalTurn(1), currentPlayer.getName()));
        }
        else // 2 players
            playText(getSIVOX(), parse(getRandomVocalTurn(2), currentPlayer.getName()));
    }

    public Player otherPlayer(){
        Player other = (currentPlayer == players[0]) ? players[1] : players[0];
        return other;
    }

    /**
     * Display the grid/tray
     */
    public void showTray() {
        Tile tile[][] = tray.getTray();

        for (int y = 0; y < tile.length; y++) {
            for (int x = 0; x < tile[y].length; x++) {

                if (tile[y][x].isAWall()) { // Walls
                    tile[y][x].getComponent().setPreferredSize(new Dimension(10, (y % 2 == 1) ? 10 : 50));

                    if ((tile[y][x].getPosition().getX() % 2 != 1 || tile[y][x].getPosition().getY() % 2 != 1)
                            && !tile[y][x].isOccupied())// Don't click the small square & occupied items
                        tile[y][x].setListenerWall(this);
                } else // 'Moves' tiles
                    tile[y][x].getComponent().setPreferredSize(new Dimension(100, 50));

                world.add(tile[y][x].getComponent(), getGridBagConstraints(x, y));
            }
        }
    }

    /**
     * Display process (GridBagLayout)
     *
     * @param x The x coordinate (grid)
     * @param y The y coordinate (grid)
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
    public void addPlayers(HashMap<String, RESOURCES> p) {
        players = new HumanPlayer[p.size()];

        int k = 0;
        for (HashMap.Entry<String, RESOURCES> e : p.entrySet())
            players[k++] = new HumanPlayer(e.getKey(), new Pawn(e.getValue()), ((k % 2 == 0) ? POSITIONS.TOP : POSITIONS.BOTTOM).getPos(), tray, this);

        currentPlayer = players[new Random().nextInt(1)];

        playText(getSIVOX(), parse(VOCAL.START, currentPlayer.getName()));
        pause(1500);
    }

    public void addPlayerAndBot(HashMap<String, RESOURCES> p) {
        players = new Player[p.size()];
        int k = 0;
        for (HashMap.Entry<String, RESOURCES> e : p.entrySet()) {
            if(e.getValue().isABot()){
                switch (botDifficulty){
                    case FACILE:
                        players[k++] = new IAEasy(new Pawn(e.getValue()), POSITIONS.TOP.getPos(), tray, this);
                        break;
                    case MOYEN:
                        players[k++] = new IAMedium(new Pawn(e.getValue()), POSITIONS.TOP.getPos(), tray, this);
                        break;
                    case DIFFICILE:
                        players[k++] = new IAHard(new Pawn(e.getValue()),POSITIONS.TOP.getPos(), tray, this);
                        break;
                }

            }
            else
                players[k++] = new HumanPlayer(e.getKey(), new Pawn(e.getValue()), POSITIONS.BOTTOM.getPos(), tray, this);
        }

        currentPlayer = players[new Random().nextInt(1)];
        if(!currentPlayer.isABot()){
            playText(getSIVOX(), parse(VOCAL.START, currentPlayer.getName()));
            pause(1500);

        }

    }

    public void pause(int time) {
        if (USE_PAUSE) {
            try {
                MovePlayerAction.setActive(false);
                Thread.sleep(time);
                MovePlayerAction.setActive(true);
            } catch (InterruptedException e) {
                return;
            }
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
        if (currentPlayer.move(d)) { // has moved
            if (currentPlayer.hasWon())
                return;

            nextTurn();
        }
    }

    /**
     * Move the player
     *
     * @param d a direction where to move
     */
    public void moveIAPlayer(DIRECTIONS d,Position position) {
        unHighlightAll();

        if (((IA)currentPlayer).getType().ordinal() >= DIFFICULTY.MOYEN.ordinal() && ((AdvancedIAs)currentPlayer).moveAndWall(d, position)) { // has moved
            if (currentPlayer.hasWon())
                return;

            nextTurn();
        }
    }
    /**
     * Move the IAEasy player
     */
    public void moveIAEasyPlayer() {
        ((IAEasy) currentPlayer).completeMove();
        if (currentPlayer.hasWon()) return;

            nextTurn();
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
     * @param settingWall We are / we are not (true / false)
     */
    public void setSettingWall(boolean settingWall) {
        this.settingWall = settingWall;
    }

    /**
     * Getter on 'are we setting walls?'
     *
     * @return true / false
     */
    public boolean isSettingWall() {
        return settingWall;
    }

    /**
     * Set the small square as a wall too
     *
     * @param gap      The direction where is the gap
     * @param position The original position
     */
    public void fillGap(DIRECTIONS gap, Position position) {
        int x = position.getX();
        int y = position.getY();

        switch (gap) {
            case RIGHT:
                if(!tray.getTile(x - 1, y).isOccupied()){
                    tray.getTile(x - 1, y).positionWall(gap);
                }
                break;

            case LEFT:
                if(!tray.getTile(x + 1, y).isOccupied()){
                    tray.getTile(x + 1, y).positionWall(gap);
                }
                break;

            case FRONT:
                if(!tray.getTile(x, y + 1).isOccupied()){
                    tray.getTile(x, y + 1).positionWall(gap);
                }
                break;

            case BACK:
                if(!tray.getTile(x, y - 1).isOccupied()){
                    tray.getTile(x, y - 1).positionWall(gap);
                }
                break;

            default:
                break;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Tray getTray() {
        return tray;
    }

    @Override
    public void dispose() {
        super.dispose();

        Game.clip.close();
        Game.clip = null;
    }
}
