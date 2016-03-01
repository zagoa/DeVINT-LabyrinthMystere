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
                tray[i][j] = new Tile(i,j,null,false);
                if(i%2 ==0 || j%2 ==0){
                    tray[i][j].getTile().setBackground(Color.black);
                }else {
                    tray[i][j].getTile().setBackground(Color.green);

                }

            }
        }

    }


    public Tile[][] getPlateau() {
        return tray;
    }

    public Tile  getXYTile(int x, int y){
        return tray[x][y];
    }



}