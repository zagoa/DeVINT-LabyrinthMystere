package dvt.labyrinth.game;

import dvt.labyrinth.actions.MovePlayerAction;
import dvt.labyrinth.actions.PutWall;
import dvt.labyrinth.model.essential.Pawn;
import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.model.player.HumanPlayer;
import dvt.labyrinth.model.player.Player;
import dvt.labyrinth.tools.ConstantesLabyrinth;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Adrian on 06/04/2016.
 */
public class Training extends Game {
    // Auto set walls
    private static final int EACH_x_SET_WALL = 3;

    // Turn
    private int time;

    public Training(HashMap<String, RESOURCES> players) {
        super(players);
        time = 0;

        playText(VOCAL.T_START.toString(), 17500);
    }

    public void update() {
        super.update();

        if (time-EACH_x_SET_WALL == 0) { // First time we set a wall
            playText(VOCAL.T_FIRST_WALL.toString(), 22000);
            ++time;
        } else if (time-EACH_x_SET_WALL < 0) {
            PutWall.setActive(false);
        }
    }

    public void playText(String sentence, int waitTime) {
        // Remove control on putting walls
        PutWall.setActive(false);
        MovePlayerAction.setActive(false);
        // Play start text
        ConstantesLabyrinth.playText(getSIVOX(), sentence);
        // Show targets
        unHighlightAll();
        setTarget();
        // Wait while talking
        pause(waitTime);
        // Set active control on putting walls
        PutWall.setActive(true);
        MovePlayerAction.setActive(true);
    }

    @Override
    public Player otherPlayer(){
        return currentPlayer;
    }

    @Override
    public void addPlayers(HashMap<String, RESOURCES> p) {
        players = new HumanPlayer[1];

        int k = 0;
        for (HashMap.Entry<String, RESOURCES> e : p.entrySet())
            players[k++] = new HumanPlayer(e.getKey(), new Pawn(e.getValue()), POSITIONS.BOTTOM.getPos(), tray,this);

        currentPlayer = players[0];
    }

    @Override
    public void nextTurn() {
        time++;

        Tile tile = tray.getTile(currentPlayer.getPosition().getX(), currentPlayer.getPosition().getY()-1);
        Tile nTile = null;

        if (time%EACH_x_SET_WALL == 0
                && !isSettingWall()
                && !tile.isOccupied()
                && checkPutWall(tile.getPosition())) { // Each x turns, we set a wall (if we can)
            tile.positionWall();

            if(gameNotBlocked(tray)) {
                DIRECTIONS dir = null;

                if ((new Random().nextInt(2)) == 1) { // First right, then left
                    if (tray.canSetAWall(DIRECTIONS.RIGHT, tile.getPosition()) && tile.getPosition().getY() % 2 == 1) {
                        dir = DIRECTIONS.RIGHT;
                        nTile = tray.getTile(tile.getPosition().getX()+2,tile.getPosition().getY());

                        nTile.positionWall();

                        if(!gameNotBlocked(tray)) { // We can't set a wall here
                            nTile.clearTile();
                            nTile = null;
                        }
                    } else if (tray.canSetAWall(DIRECTIONS.LEFT, tile.getPosition()) && tile.getPosition().getY() % 2 == 1) {
                        dir = DIRECTIONS.LEFT;
                        nTile = tray.getTile(tile.getPosition().getX()-2, tile.getPosition().getY());

                        nTile.positionWall();

                        if(!gameNotBlocked(tray)) {
                            nTile.clearTile();
                            nTile = null;
                        }

                    }
                } else { // First left, then right
                    if (tray.canSetAWall(DIRECTIONS.LEFT, tile.getPosition()) && tile.getPosition().getY() % 2 == 1) {
                        dir = DIRECTIONS.LEFT;
                        nTile = tray.getTile(tile.getPosition().getX()-2, tile.getPosition().getY());

                        nTile.positionWall();

                        if(!gameNotBlocked(tray)) {
                            nTile.clearTile();
                            nTile = null;
                        }
                    } else if (tray.canSetAWall(DIRECTIONS.RIGHT, tile.getPosition()) && tile.getPosition().getY() % 2 == 1) {
                        dir = DIRECTIONS.RIGHT;
                        nTile = tray.getTile(tile.getPosition().getX()+2,tile.getPosition().getY());

                        nTile.positionWall();

                        if(!gameNotBlocked(tray)) { // We can't set a wall here
                            nTile.clearTile();
                            nTile = null;
                        }
                    }
                }

                if (nTile != null) { // Set a wall
                    fillGap(dir, nTile.getPosition());
                }
            } else
                tile.clearTile();
        }
    }

    /**
     * Set a winner, with the specific view
     */
    public void win() {
        this.dispose(); // Delete this view
        new WinGame(currentPlayer, VOCAL.T_WIN.toString()).loop(); // Add win view
    }
}
