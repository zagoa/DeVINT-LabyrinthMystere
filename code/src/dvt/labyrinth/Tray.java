package dvt.labyrinth;

import dvt.labyrinth.model.DefaultItem;
import dvt.labyrinth.model.Item;

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
                tray[y][x] = new Tile(new DefaultItem(x, y));
            }
        }
    }

    public Tile[][] getTray() {
        return tray;
    }

    public Tile getTile(int x, int y) {
        return tray[y][x];
    }

    public void movePlayer(int xBefore, int yBefore, int xAfter, int yAfter) {
        tray[yAfter][xAfter].setItem(tray[yBefore][xBefore].getItem());
        tray[yBefore][xBefore].setItem(new DefaultItem(xBefore, yBefore));
    }
}