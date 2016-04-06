package dvt.labyrinth.game;

import dvt.labyrinth.model.essential.Pawn;
import dvt.labyrinth.model.essential.Tile;
import dvt.labyrinth.model.player.HumanPlayer;
import dvt.labyrinth.model.player.Player;

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

        // TODO : SIVOX pour l'entrainement
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
            players[k++] = new HumanPlayer(e.getKey(), new Pawn(e.getValue()), POSITIONS.BOTTOM.getPos(), tray);

        currentPlayer = players[0];
    }

    @Override
    public void nextTurn() {
        time++;

        Tile tile = tray.getTile(currentPlayer.getPosition().getX(), currentPlayer.getPosition().getY()-1);

        if (time%EACH_x_SET_WALL == 0
                && !isSettingWall()
                && !tile.isOccupied()
                && checkPutWall(tile.getPosition())) { // Each x turns, we set a wall (if we can)
            tile.positionWall();

            if(gameNotBlocked(tray)) {
                Tile nTile = null;
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

                if (nTile != null)
                    fillGap(dir, nTile.getPosition());
            } else
                tile.clearTile();
        }
    }
}
