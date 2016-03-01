package dvt.labyrinth;

import java.awt.*;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tray {
    private final int nbCase = 17;//nombre de cases par lignes, prenant en compte les Tile Pawn+les Tile wall
    private Tile tray[][];


    public Tray() {
        tray = new Tile[17][17];
        for (int i = 0; i < nbCase; i++) {
            for (int j = 0; j < nbCase; j++) {
                tray[i][j] = new Tile(i, j,null);

            }
        }

    }


    public Tile[][] getTray() {
        return tray;
    }



}