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
                RESSOURCES r = (x % 2 == 1 || y % 2 == 1) ? RESSOURCES.WALL : RESSOURCES.TRANSPARENT;

                tray[y][x] = new Tile(x, y, new DefaultItem(r, x, y));
            }
        }
    }

    public Tile[][] getTray() {
        return tray;
    }

}