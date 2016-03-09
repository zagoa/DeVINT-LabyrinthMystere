package dvt.labyrinth;

import dvt.labyrinth.model.DefaultItem;
import dvt.labyrinth.model.Item;
import dvt.labyrinth.model.Pawn;

import java.awt.*;
import static dvt.labyrinth.ConstantesLabyrinth.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tray {
    private Tile tray[][];

    public Tray() {
        tray = new Tile[17][17];
        for (int y = 0; y < NBRE_CASES; y++) {
            for (int x = 0; x < NBRE_CASES; x++) {
                // On a wall or not ?
                tray[y][x] = new Tile(new DefaultItem(), new Position(x,y));
            }
        }
    }

    public Tile[][] getTray() {
        return tray;
    }

    public Tile getTile(int x, int y) {
        return tray[y][x];
    }

    public Tile getTile(Position position) {
        return getTile(position.getX(), position.getY());
    }

    public void placePlayer(int x, int y, Item player) {
        tray[y][x].setItem(player);
    }
}