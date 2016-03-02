package dvt.labyrinth.model.Tray;

import dvt.devint.Fenetre;
import dvt.labyrinth.Tile;
import dvt.labyrinth.model.Pawn;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Arnaud on 26/02/2016.
 */
public class Tray extends Fenetre {
    private final int nbCase = 17;//nombre de cases par lignes, prenant en compte les Tile Pawn+les Tile wall
    private Tile tray[][];


    public Tray() {
        tray = new Tile[17][17];
        for (int i = 0; i < nbCase; i++) {
            for (int j = 0; j < nbCase; j++) {
                tray[i][j] = new Tile(i, j,null,false);
            }
        }
    }

    public Tile[][] getTray() {
        return tray;
    }

    public Tile  getXYTile(int x, int y){
        return tray[x][y];
    }

    public void movePawn(){

        addControlDown(MouseEvent.BUTTON1, new LeftClickAction(this));
        addControlUp(MouseEvent.BUTTON1, new LeftClickAction(this));
    }


    public void moveWall(){

        addControlDown(MouseEvent.BUTTON3, new RightClickAction(this));
        addControlUp(MouseEvent.BUTTON3, new RightClickAction(this));
    }







}